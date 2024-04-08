public class Cylinder {
    private double radius;
    private double height;

    public Cylinder() {
    }

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double baseArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double wallArea() {
        return 2 * Math.PI * radius * height;
    }

    public double totalArea() {
        return 2 * baseArea() + wallArea();
    }

    public double volume() {
        return baseArea() * height;
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
