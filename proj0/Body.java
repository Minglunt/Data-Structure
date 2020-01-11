public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xxP, double yyP, double xxV, double yyV, double m, String img) {
    	xxPos = xxP;
    	yyPos = yyP;
    	xxVel = xxV;
    	yyVel = yyV;
    	mass = m;
    	imgFileName = img;
    }

    public Body(Body b) {
    	xxPos = b.xxPos;
    	yyPos = b.yyPos;
    	xxVel = b.xxVel;
    	yyVel = b.yyVel;
    	mass = b.mass;
    	imgFileName = b.imgFileName;
    }

    public double calcDistance(Body c) {
    	double dxx = xxPos - c.xxPos;
    	double dyy = yyPos - c.yyPos;
    	double r = Math.sqrt(Math.pow(dxx, 2) + Math.pow(dyy, 2));
    	return r;
	}

	public double calcForceExertedBy(Body d) {
    	double r = calcDistance(d);
    	return 6.67 * Math.pow(10, -11) * mass * d.mass / Math.pow(r, 2);
	}

	public double calcForceExertedByX(Body b) {
    	double f = calcForceExertedBy(b);
    	return f * (-xxPos + b.xxPos)/calcDistance(b);
	}

	public double calcForceExertedByY(Body b) {
		double f = calcForceExertedBy(b);
		return f * (-yyPos + b.yyPos)/calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] allBody) {
    	double sum = 0;
    	for (int i = 0; i < allBody.length; i++) {
    		if (this.equals(allBody[i])) {
    			continue;
			}
    		sum += calcForceExertedByX(allBody[i]);
		}
    	return sum;
	}

	public double calcNetForceExertedByY(Body[] allBody) {
		double sum = 0;
		for (int i = 0; i < allBody.length; i++) {
			if (this.equals(allBody[i])) {
				continue;
			}
			sum += calcForceExertedByY(allBody[i]);
		}
		return sum;
	}

	public void update(double t, double fx, double fy) {
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel += ax * t;
		yyVel += ay * t;
		xxPos += xxVel * t;
		yyPos += yyVel * t;
	}

	public void draw(){
    	String imgname = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imgname);
	}
        }