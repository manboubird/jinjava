package com.hubspot.jinjava.el;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.Context;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.interpret.TemplateError;
import com.hubspot.jinjava.interpret.TemplateError.ErrorReason;
import com.hubspot.jinjava.objects.PyWrapper;
import com.hubspot.jinjava.objects.date.PyishDate;

@SuppressWarnings("unchecked")
public class ExpressionResolverTest {

  private JinjavaInterpreter interpreter;
  private Context context;

  @Before
  public void setup() {
    interpreter = new Jinjava().newInterpreter();
    context = interpreter.getContext();
  }

  @Test
  public void itResolvesListLiterals() throws Exception {
    Object val = interpreter.resolveELExpression("['0.5','50']", -1);
    List<Object> list = (List<Object>) val;
    assertThat(list).containsExactly("0.5", "50");
  }

  @Test
  public void itResolvesImmutableListLiterals() throws Exception {
    Object val = interpreter.resolveELExpression("('0.5','50')", -1);
    List<Object> list = (List<Object>) val;
    assertThat(list).containsExactly("0.5", "50");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testTuplesAreImmutable() throws Exception {
    Object val = interpreter.resolveELExpression("('0.5','50')", -1);
    List<Object> list = (List<Object>) val;
    list.add("foo");
  }

  @Test
  public void itCanCompareStrings() throws Exception {
    context.put("foo", "white");
    assertThat(interpreter.resolveELExpression("'2013-12-08 16:00:00+00:00' > '2013-12-08 13:00:00+00:00'", -1)).isEqualTo(Boolean.TRUE);
    assertThat(interpreter.resolveELExpression("foo == \"white\"", -1)).isEqualTo(Boolean.TRUE);
  }

  @Test
  public void itResolvesUntrimmedExprs() throws Exception {
    context.put("foo", "bar");
    Object val = interpreter.resolveELExpression("  foo ", -1);
    assertThat(val).isEqualTo("bar");
    assertThat(interpreter.getContext().wasExpressionResolved("foo")).isTrue();
  }

  @Test
  public void itResolvesMathVals() throws Exception {
    context.put("i_am_seven", 7L);
    Object val = interpreter.resolveELExpression("(i_am_seven * 2 + 1)/3", -1);
    assertThat(val).isEqualTo(5.0);
    assertThat(interpreter.getContext().wasValueResolved("i_am_seven")).isTrue();
  }

  @Test
  public void itResolvesListVal() throws Exception {
    context.put("thelist", Lists.newArrayList(1L, 2L, 3L));
    Object val = interpreter.resolveELExpression("thelist[1]", -1);
    assertThat(val).isEqualTo(2L);
  }

  @Test
  public void itResolvesDictValWithBracket() throws Exception {
    Map<String, Object> dict = Maps.newHashMap();
    dict.put("foo", "bar");
    context.put("thedict", dict);

    Object val = interpreter.resolveELExpression("thedict['foo']", -1);
    assertThat(val).isEqualTo("bar");
    assertThat(interpreter.getContext().wasExpressionResolved("thedict['foo']")).isTrue();
  }

  @Test
  public void itResolvesDictValWithDotParam() throws Exception {
    Map<String, Object> dict = Maps.newHashMap();
    dict.put("foo", "bar");
    context.put("thedict", dict);

    Object val = interpreter.resolveELExpression("thedict.foo", -1);
    assertThat(val).isEqualTo("bar");
    assertThat(interpreter.getContext().wasExpressionResolved("thedict.foo")).isTrue();
  }

  @Test
  public void itResolvesInnerDictVal() throws Exception {
    Map<String, Object> dict = Maps.newHashMap();
    Map<String, Object> inner = Maps.newHashMap();
    inner.put("test", "val");
    dict.put("inner", inner);
    context.put("thedict", dict);

    Object val = interpreter.resolveELExpression("thedict.inner[\"test\"]", -1);
    assertThat(val).isEqualTo("val");
  }

  @Test
  public void itResolvesInnerListVal() throws Exception {
    Map<String, Object> dict = Maps.newHashMap();
    List<String> inner = Lists.newArrayList("val");
    dict.put("inner", inner);
    context.put("thedict", dict);

    Object val = interpreter.resolveELExpression("thedict.inner[0]", -1);
    assertThat(val).isEqualTo("val");
  }

  public static class MyCustomList<T> extends ForwardingList<T> implements PyWrapper {
    private final List<T> list;

    public MyCustomList(List<T> list) {
      this.list = list;
    }

    @Override
    protected List<T> delegate() {
      return list;
    }

    public int getTotalCount() {
      return list.size();
    }
  }

  @Test
  public void itRecordsFilterNames() throws Exception {
    Object val = interpreter.resolveELExpression("2.3 | round", -1);
    assertThat(val).isEqualTo(new BigDecimal(2));
    assertThat(interpreter.getContext().wasValueResolved("filter:round")).isTrue();
  }

  @Test
  public void callCustomListProperty() throws Exception {
    List<Integer> myList = new MyCustomList<>(Lists.newArrayList(1, 2, 3, 4));

    context.put("mylist", myList);
    Object val = interpreter.resolveELExpression("mylist.total_count", -1);
    assertThat(val).isEqualTo(4);
  }

  @Test
  public void complexInWithOrCondition() throws Exception {
    context.put("foo", "this is<hr>something");
    context.put("bar", "this is<hr/>something");

    assertThat(interpreter.resolveELExpression("\"<hr>\" in foo or \"<hr/>\" in foo", -1)).isEqualTo(true);
    assertThat(interpreter.resolveELExpression("\"<hr>\" in bar or \"<hr/>\" in bar", -1)).isEqualTo(true);
    assertThat(interpreter.resolveELExpression("\"<har>\" in foo or \"<har/>\" in foo", -1)).isEqualTo(false);
  }

  @Test
  public void unknownProperty() throws Exception {
    interpreter.resolveELExpression("foo", 23);
    assertThat(interpreter.getErrors()).isEmpty();

    context.put("foo", new Object());
    interpreter.resolveELExpression("foo.bar", 23);

    assertThat(interpreter.getErrors()).hasSize(1);

    TemplateError e = interpreter.getErrors().get(0);
    assertThat(e.getReason()).isEqualTo(ErrorReason.UNKNOWN);
    assertThat(e.getLineno()).isEqualTo(23);
    assertThat(e.getFieldName()).isEqualTo("bar");
    assertThat(e.getMessage()).contains("Cannot resolve property 'bar'");
  }

  @Test
  public void syntaxError() throws Exception {
    interpreter.resolveELExpression("(*&W", 123);
    assertThat(interpreter.getErrors()).hasSize(1);

    TemplateError e = interpreter.getErrors().get(0);
    assertThat(e.getReason()).isEqualTo(ErrorReason.SYNTAX_ERROR);
    assertThat(e.getLineno()).isEqualTo(123);
    assertThat(e.getMessage()).contains("invalid character");
  }

  @Test
  public void itWrapsDates() throws Exception {
    context.put("myobj", new MyClass(new Date(0)));
    Object result = interpreter.resolveELExpression("myobj.date", -1);
    assertThat(result).isInstanceOf(PyishDate.class);
    assertThat(result.toString()).isEqualTo("1970-01-01 00:00:00");
  }

  @Test
  public void blackListedProperties() throws Exception {
    context.put("myobj", new MyClass(new Date(0)));
    interpreter.resolveELExpression("myobj.class.methods[0]", -1);

    assertThat(interpreter.getErrors()).isNotEmpty();
    TemplateError e = interpreter.getErrors().get(0);
    assertThat(e.getReason()).isEqualTo(ErrorReason.UNKNOWN);
    assertThat(e.getFieldName()).isEqualTo("class");
    assertThat(e.getMessage()).contains("Cannot resolve property 'class'");
  }

  @Test
  public void blackListedMethods() throws Exception {
    context.put("myobj", new MyClass(new Date(0)));
    interpreter.resolveELExpression("myobj.wait()", -1);

    assertThat(interpreter.getErrors()).isNotEmpty();
    TemplateError e = interpreter.getErrors().get(0);
    assertThat(e.getMessage()).contains("Cannot find method 'wait'");
  }

  @Test
  public void presentOptionalProperty() {
    context.put("myobj", new OptionalProperty(null, "foo"));
    assertThat(interpreter.resolveELExpression("myobj.val", -1)).isEqualTo("foo");
    assertThat(interpreter.getErrors()).isEmpty();
  }

  @Test
  public void emptyOptionalProperty() {
    context.put("myobj", new OptionalProperty(null, null));
    assertThat(interpreter.resolveELExpression("myobj.val", -1)).isNull();
    assertThat(interpreter.getErrors()).isEmpty();
  }

  @Test
  public void presentNestedOptionalProperty() {
    context.put("myobj", new OptionalProperty(new MyClass(new Date(0)), "foo"));
    assertThat(Objects.toString(interpreter.resolveELExpression("myobj.nested.date", -1))).isEqualTo("1970-01-01 00:00:00");
    assertThat(interpreter.getErrors()).isEmpty();
  }

  @Test
  public void emptyNestedOptionalProperty() {
    context.put("myobj", new OptionalProperty(null, null));
    assertThat(interpreter.resolveELExpression("myobj.nested.date", -1)).isNull();
    assertThat(interpreter.getErrors()).isEmpty();
  }

  @Test
  public void presentNestedNestedOptionalProperty() {
    context.put("myobj", new NestedOptionalProperty(new OptionalProperty(new MyClass(new Date(0)), "foo")));
    assertThat(Objects.toString(interpreter.resolveELExpression("myobj.nested.nested.date", -1))).isEqualTo("1970-01-01 00:00:00");
    assertThat(interpreter.getErrors()).isEmpty();
  }

  public static final class MyClass {
    private Date date;

    MyClass(Date date) {
      this.date = date;
    }

    public Date getDate() {
      return date;
    }
  }

  public static final class OptionalProperty {
    private MyClass nested;
    private String val;

    OptionalProperty(MyClass nested, String val) {
      this.nested = nested;
      this.val = val;
    }

    public Optional<MyClass> getNested() {
      return Optional.ofNullable(nested);
    }

    public Optional<String> getVal() {
      return Optional.ofNullable(val);
    }
  }

  public static final class NestedOptionalProperty {
    private OptionalProperty nested;

    public NestedOptionalProperty(OptionalProperty nested) {
      this.nested = nested;
    }

    public Optional<OptionalProperty> getNested() {
      return Optional.ofNullable(nested);
    }
  }
}
