package base;

public class Rectangle {
    Vector2D position;
    int width;
    int height;
    public Rectangle(){
        this(new Vector2D(), 5,5);
    }

    public Rectangle(Vector2D position, int width,int height){
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public int top(){
        return (int) this.position.y;
    }

    public  int bottom(){
        return (this.top() + this.height);
    }

    public int left(){
        return (int) this.position.x;
    }

    public  int right(){
        return  this.left() + this.width;
    }

    public boolean intersects(Rectangle other){
        boolean intersectX = this.left() <= other.right() && other.left() <= this.right();
        boolean intersectY = this.top() <= other.bottom() && other.top() <= this.bottom();
        return intersectX && intersectY;
    }
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(new Vector2D(5,5),10,10);
        Rectangle rect2 = new Rectangle(new Vector2D(5,5),10,10);
        Rectangle rect3 = new Rectangle(new Vector2D(-5,-5),10,10);
        if(rect3.intersects(rect1)){
            System.out.println("dung");
        }else{
            System.out.println("sai");
        }
    }
}
