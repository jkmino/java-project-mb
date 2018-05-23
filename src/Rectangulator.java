public class Rectangulator {
  public static void main (String[] args){
    int length = Integer.parseInt(args[0]);
    int width = Integer.parseInt(args[1]);
    Rectangle myrectangle = new Rectangle(length, width);

    String output = String.format("**** your rectangle  **** \n\n length:  %d \nArea: %d\nPerimeter: %d\n\n  ", myrectangle.length , myrectangle.width, myrectangle.getArea(),myrectangle.getPerimeter());

    system.out.println(output);

  }

}
