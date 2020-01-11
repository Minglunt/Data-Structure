public class NBody {
    public static double readRadius(String filename) {
        In file = new In(filename);
        int num = file.readInt();
        return file.readDouble();
    }

    public static Body[] readBodies(String filename) {
        In file = new In(filename);
        int num = file.readInt();
        Body[] bodies = new Body[num];
        double raius = file.readDouble();
        for (int i=0; i<num; i++) {
            double px = file.readDouble();
            double py = file.readDouble();
            double vx = file.readDouble();
            double vy = file.readDouble();
            double m = file.readDouble();
            String fname = file.readString();
            bodies[i] = new Body(px, py, vx, vy, m, fname);
        }
        return bodies;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        String background = "images/starfield.jpg";

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        for (double t=0; t<=T; t+=dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i=0; i<bodies.length; i++) {
                Body body = bodies[i];
                xForces[i] = body.calcNetForceExertedByX(bodies);
                yForces[i] = body.calcNetForceExertedByY(bodies);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, background);
            for (int j=0; j<bodies.length; j++) {
                Body planet = bodies[j];
                planet.update(dt, xForces[j], yForces[j]);
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }

}
