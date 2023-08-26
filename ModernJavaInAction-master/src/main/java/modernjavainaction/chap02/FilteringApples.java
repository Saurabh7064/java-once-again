package modernjavainaction.chap02;

import javaonceagain.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilteringApples {

  public static void main(String... args) {
    List<Apple> inventory = Arrays.asList(
        new Apple(80, Color.GREEN),
        new Apple(155, Color.GREEN),
        new Apple(120, Color.RED));
//without java 8
    // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
    List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
    System.out.println(greenApples);
//without java 8
    // [Apple{color=RED, weight=120}]
    List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
    System.out.println(redApples);
//with java 8 using predicate for color
    // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
    List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
    System.out.println(greenApples2);

//with java 8 using predicate for weight
    // [Apple{color=GREEN, weight=155}]
    List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
    System.out.println(heavyApples);

//with java 8 using predicate for weight and color
    List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
    System.out.println(redAndHeavyApples);
//with java annoymous class

    // [Apple{color=RED, weight=120}]
    List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
      @Override
      public boolean test(Apple a) {
        return a.getColor() == Color.RED;
      }
    });
    System.out.println(redApples2);

    List<Apple> redApples3 =
            filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
    System.out.println("redApples3 "+redApples3);

    List<Integer> evenNumbers =
            filter(Arrays.asList(1,2,3,4,5), (Integer i) -> i % 2 == 0);

    System.out.println("evenNumbers"+evenNumbers);
  }

  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColor() == Color.GREEN) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColor() == color) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  enum Color {
    RED,
    GREEN
  }

  public static class Apple {

    private int weight = 0;
    private Color color;

    public Apple(int weight, Color color) {
      this.weight = weight;
      this.color = color;
    }

    public int getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }

    public Color getColor() {
      return color;
    }

    public void setColor(Color color) {
      this.color = color;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString() {
      return String.format("Apple{color=%s, weight=%d}", color, weight);
    }

  }

  interface ApplePredicate {

    boolean test(Apple a);

  }

  static class AppleWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getWeight() > 150;
    }

  }

  static class AppleColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.GREEN;
    }

  }

  static class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.RED && apple.getWeight() > 150;
    }

  }
  public interface Predicate<T> {
    boolean test(T t);
  }
  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> result = new ArrayList<>();
    for(T e: list) {
      if(p.test(e)) {
        result.add(e);
      }
    }
    return result;
  }
//with streams and filter
  public static <T> List<T> filter2(List<T> list, Predicate<T> p){
    return list.stream().filter(p::test).collect(Collectors.toList());
  }

}
