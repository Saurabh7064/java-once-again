package javaonceagain;

import modernjavainaction.chap02.FilteringApples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    List<Apple> appleList = Arrays.asList(new Apple(10,Color.RED),
            new Apple(2,Color.GREEN),
            new Apple(15,Color.RED));

    interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
         return list.stream().filter(p::test).collect(Collectors.toList());
    }

    public static void main(String[] args) {


     }

    enum Color{
        RED,
        GREEN
    }

    public static class Apple {

        private int weight = 0;
        private  Color color;

        public Apple(int weight,  Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public  Color getColor() {
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
}
