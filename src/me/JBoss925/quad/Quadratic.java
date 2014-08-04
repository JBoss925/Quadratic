package me.JBoss925.quad;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class Quadratic implements Cloneable{

    double a;
    double b;
    double c;
    double startx, endx, changeinterval;
    Location base;
    List<Location> locs;
    List<Block> blocks;


    public Quadratic(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Quadratic(double a, double b, double c, double startx, double endx, double changeinterval, Location base){
        this.a = a;
        this.b = b;
        this.c = c;
        this.startx = startx;
        this.endx = endx;
        this.changeinterval = changeinterval;
        this.base = base;
    }

    public Quadratic(double a, double b, double c, double startx, double endx, double changeinterval, Location base, List<Location> locs, List<Block> blocks){
        this.a = a;
        this.b = b;
        this.c = c;
        this.startx = startx;
        this.endx = endx;
        this.changeinterval = changeinterval;
        this.base = base;
        this.locs = locs;
        this.blocks = blocks;
    }

    public Quadratic(double a, double b, double c, double startx, double endx, double changeinterval){
        this.a = a;
        this.b = b;
        this.c = c;
        this.startx = startx;
        this.endx = endx;
        this.changeinterval = changeinterval;
    }

    public Quadratic(int a, int b, int c){
        this.a = Double.parseDouble(String.valueOf(a).concat(".0"));
        this.b = Double.parseDouble(String.valueOf(b).concat(".0"));
        this.c = Double.parseDouble(String.valueOf(c).concat(".0"));
    }

    /**
     * Used to get the y value at a single x.
     *
     * @param x - the x of the quadratic the y will be retrieved from
     * @return - a double value of the non-coordinate-adjusted y at the given x
     */
    public double getY(double x){
        double d = Math.pow(x, 2.0) * this.a;
        if(d == 0){
            d = 1.0;
        }
        d = this.b * x + d;
        if(d == 0){
            d = 1.0;
        }
        d = d + this.c;
        return d;
    }

    /**
     * Used to get the x intercepts of the quadratic.
     *
     * @return - list of doubles(2) of the non-coordinate-adjusted x intercepts of the quadratic (where y = 0)
     */
    public List<Double> getXIntercepts(){
        List<Double> ds = new ArrayList<Double>();
        double a = this.b * -1;
        if(a == 0){
            a = 1.0;
        }
        double mod = this.a * this.c * 4;
        if(mod == 0){
            mod = 1.0;
        }
        double mod2 = this.a * 2;
        if(mod2 == 0){
            mod = 1.0;
        }
        double mod3 = Math.pow(this.b, 2.0);
        if(mod3 == 0){
            mod3 = 1.0;
        }
        if(mod3 - mod < 0){
            a = a + Math.sqrt(Math.abs(mod3 - mod));
            a = a / mod2;
            ds.add(a);
        }
        if(mod3 - mod >= 0){
            a = a + Math.sqrt(mod3 - mod);
            a = a / mod2;
            ds.add(a);
        }
        double b = this.b * -1;
        if(b == 0){
            b = 1.0;
        }
        double modb = this.a * this.c * 4;
        if(modb == 0){
            modb = 1.0;
        }
        double mod2b = this.a * 2;
        if(mod2b == 0){
            mod2b = 1.0;
        }
        double mod3b = Math.pow(this.b, 2.0);
        if(mod3b == 0){
            mod3b = 1.0;
        }
        if(mod3b - modb < 0){
            b = b - Math.sqrt(Math.abs(mod3b - modb));
            b = b / mod2b;
            ds.add(b);
        }
        if(mod3b - modb >= 0){
            b = b + Math.sqrt(mod3b - modb);
            b = b / mod2b;
            ds.add(b);
        }
        return ds;
    }

    /**
     * Used to get the non-coordinate-adjusted y values from the startx to the endx at a rate of the changeinterval.
     *
     * @param startx - the smallest x value that will be used to find y values
     * @param endx - the largest x value that will be used to find y values
     * @param changeinterval - the rate at which the x increases from startx to endx
     * @return - list of doubles of the non-coordinate-adjusted y values
     */
    public List<Double> getYValues(double startx, double endx, double changeinterval){
        List<Double> vals = new ArrayList<Double>();
        for(double x = startx; x <= endx;){
            double d = Math.pow(x, 2.0) * this.a;
            if(d == 0){
                d = 1.0;
            }
            d = this.b * x + d;
            if(d == 0){
                d = 1.0;
            }
            d = d + this.c;
            vals.add(d);
            x = x + changeinterval;
        }
        return vals;
    }

    /**
     * Used to get the locations of points in a quadratic.
     *
     * @param startx - the smallest x value that will be used to find y values
     * @param endx - the largest x value that will be used to find y values
     * @param changeinterval - the rate at which the x increases from startx to endx
     * @param baseX - the x that is added to the non-coordinate-adjusted to find the block at a location
     * @param baseY - the y that is added to the non-coordinate-adjusted to find the block at a location
     * @param baseZ - the z that is added to the non-coordinate-adjusted to find the block at a location
     * @param w - the world the blocks originate within
     * @param rotateForZAsXAxis - boolean as to whether or not the quadratic will be rotated to be aligned with the z axis
     * @return - list of locations in a coordinate-aligned quadratic
     */
    public List<Location> getLocationsInCoordinateAlignedQuadratic(double startx, double endx, double changeinterval, double baseX, double baseY, double baseZ, World w, boolean rotateForZAsXAxis){
        List<Location> vals = new ArrayList<Location>();
        if(!rotateForZAsXAxis){
            for(double x = startx; x <= endx;){
                double d = Math.pow(x, 2.0) * this.a;
                if(d == 0){
                    d = 1.0;
                }
                d = this.b * x + d;
                if(d == 0){
                    d = 1.0;
                }
                d = d + this.c;
                Location loc = new Location(w, x + baseX, d + baseY, baseZ);
                vals.add(loc);
                x = x + changeinterval;
            }
        }
        if(rotateForZAsXAxis){
            for(double x = startx; x <= endx;){
                double d = Math.pow(x, 2.0) * this.a;
                if(d == 0){
                    d = 1.0;
                }
                d = this.b * x + d;
                if(d == 0){
                    d = 1.0;
                }
                d = d + this.c;
                Location loc = new Location(w, baseX, d + baseY, baseZ + x);
                vals.add(loc);
                x = x + changeinterval;
            }
        }
        locs = vals;
        return vals;
    }

    /**
     * Used to find locations in a parabola that is rotated to the player's yaw.
     *
     * @param p - player who's yaw a location will be used to compute
     * @param startx - the smallest x value that will be used to find y values
     * @param endx - the largest x value that will be used to find y values
     * @param changeinterval - the rate at which the x increases from startx to endx
     * @return - list of locations that have been aligned with the player's yaw
     */
    public List<Location> getLocationsInPlayerAlignedParabola(Player p, double startx, double endx, double changeinterval){
        List<Location> locs = getLocationsInCoordinateAlignedQuadratic(startx, endx, changeinterval, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getWorld(), false);
        List<Location> newlocs = new ArrayList<Location>();
        Location loca = p.getLocation();
        for(Location loc : locs){
            double rotatedX = Math.cos(loca.getYaw()) * (loc.getX() - loca.getX()) - Math.sin(loca.getYaw()) * (loc.getZ()-loca.getZ()) + loca.getX();
            double rotatedZ = Math.sin(loca.getYaw()) * (loc.getX() - loca.getX()) + Math.cos(loca.getYaw()) * (loc.getZ() - loca.getZ()) + loca.getZ();
            newlocs.add(new Location(p.getWorld(), rotatedX, loc.getY(), rotatedZ));
        }
        locs = newlocs;
        return newlocs;
    }

    /**
     * Used to get the y value of the y intercept.
     *
     * @return - the y value of the y intercept(where x = 0)
     */
    public double getYIntercept(){
        return this.c;
    }

    /**
     * Used to get the blocks in a quadratic aligned with the x or z axis.
     *
     * @param startx - the smallest x value that will be used to find y values
     * @param endx - the largest x value that will be used to find y values
     * @param changeinterval - the rate at which the x increases from startx to endx
     * @param baseX - the x that is added to the non-coordinate-adjusted to find the block at a location
     * @param baseY - the y that is added to the non-coordinate-adjusted to find the block at a location
     * @param baseZ - the z that is added to the non-coordinate-adjusted to find the block at a location
     * @param w - the world the blocks originate within
     * @param rotateForZAsXAxis - boolean as to whether or not the quadratic will be rotated to be aligned with the z axis
     * @return - list of blocks the quadratic passes through
     */
    public List<Block> getBlocksInCoordinateAlignedQuadratic(double startx, double endx, double changeinterval, double baseX, double baseY, double baseZ, World w, boolean rotateForZAsXAxis){
        List<Block> vals = new ArrayList<Block>();
        if(!rotateForZAsXAxis){
            for(double x = startx; x <= endx;){
                double d = Math.pow(x, 2.0) * this.a;
                if(d == 0){
                    d = 1.0;
                }
                d = this.b * x + d;
                if(d == 0){
                    d = 1.0;
                }
                d = d + this.c;
                vals.add(w.getBlockAt(new Location(w, baseX + x, baseY + d, baseZ)));
                x = x + changeinterval;
            }
        }
        if(rotateForZAsXAxis){
            for(double x = startx; x <= endx;){
                double d = Math.pow(x, 2.0) * this.a;
                if(d == 0){
                    d = 1.0;
                }
                d = this.b * x + d;
                if(d == 0){
                    d = 1.0;
                }
                d = d + this.c;
                vals.add(w.getBlockAt(new Location(w, baseX, baseY + d, baseZ + x)));
                x = x + changeinterval;
            }
        }
        blocks = vals;
        return vals;
    }

    /**
     * Used to get the blocks in a quadratic aligned with the player.
     *
     * @param p - player who's yaw a location will be used to compute
     * @param startx - the smallest x value that will be used to find y values
     * @param endx - the largest x value that will be used to find y values
     * @param changeinterval - the rate at which the x increases from startx to endx
     * @return - list of blocks in a parabola aligned with the player
     */
    public List<Block> getBlocksInPlayerAlignedParabola(Player p, double startx, double endx, double changeinterval){
        List<Location> locs = getLocationsInCoordinateAlignedQuadratic(startx, endx, changeinterval, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getWorld(), false);
        List<Location> newlocs = new ArrayList<Location>();
        Location loca = p.getLocation();
        for(Location loc : locs){
            double rotatedX = Math.cos(loca.getYaw()) * (loc.getX() - loca.getX()) - Math.sin(loca.getYaw()) * (loc.getZ()-loca.getZ()) + loca.getX();
            double rotatedZ = Math.sin(loca.getYaw()) * (loc.getX() - loca.getX()) + Math.cos(loca.getYaw()) * (loc.getZ() - loca.getZ()) + loca.getZ();
            newlocs.add(new Location(p.getWorld(), rotatedX, loc.getY(), rotatedZ));
        }
        List<Block> blocks1 = new ArrayList<Block>();
        for(Location loc : newlocs){
            blocks1.add(p.getWorld().getBlockAt(loc));
        }
        blocks = blocks1;
        return blocks1;
    }

    /**
     * Used for rotating a list of locations about a location a given amount of degrees.
     *
     * @param rotatedegrees - degrees the locations will be rotated
     * @param rotateAround - locations the locations will be rotated about
     * @param locations - list of locations
     * @return - returns a list of new locations
     */
    public List<Location> rotateLocationsAboutYAxis(float rotatedegrees, Location rotateAround, List<Location> locations){
        List<Location>  newlocs = new ArrayList<Location>();
        Location loca = new Location(rotateAround.getWorld(), rotateAround.getX(), rotateAround.getY(), rotateAround.getZ());
        for(Location loc : locations){
            double rotatedX = Math.cos(rotatedegrees) * (loc.getX() - loca.getX()) - Math.sin(rotatedegrees) * (loc.getZ()-loca.getZ()) + loca.getX();
            double rotatedZ = Math.sin(rotatedegrees) * (loc.getX() - loca.getX()) + Math.cos(rotatedegrees) * (loc.getZ() - loca.getZ()) + loca.getZ();
            newlocs.add(new Location(loca.getWorld(), rotatedX, loc.getY(), rotatedZ));
        }
        locs = newlocs;
        return newlocs;
    }

    /**
     * This method requires that you've used methods that set the glocal List<Location> locs and Location base or that you've set the global List<Location> locs and Location base.
     * Used to rotate locations in a quadratic.
     *
     * @param rotatedegrees - degrees the locations will be rotated
     * @return - returns a list of new locations
     */
    public List<Location> rotateAboutYAxis(float rotatedegrees){
        List<Location>  newlocs = new ArrayList<Location>();
        Location loca = new Location(base.getWorld(), base.getX(), base.getY(), base.getZ());
        for(Location loc : locs){
            double rotatedX = Math.cos(rotatedegrees) * (loc.getX() - loca.getX()) - Math.sin(rotatedegrees) * (loc.getZ()-loca.getZ()) + loca.getX();
            double rotatedZ = Math.sin(rotatedegrees) * (loc.getX() - loca.getX()) + Math.cos(rotatedegrees) * (loc.getZ() - loca.getZ()) + loca.getZ();
            newlocs.add(new Location(loca.getWorld(), rotatedX, loc.getY(), rotatedZ));
        }
        locs = newlocs;
        return newlocs;
    }

    /**
     * Used to rotate a quadratic about the x axis
     *
     * @param rotatedegrees - degrees the locations will be rotated
     * @param rotateAround - locations the locations will be rotated about
     * @param locations - list of locations
     * @return - list of locations rotated about the X axis
     */
    public List<Location> rotateLocationsAboutXAxis(float rotatedegrees, Location rotateAround, List<Location> locations){
        List<Location>  newlocs = new ArrayList<Location>();
        Location loca = new Location(rotateAround.getWorld(), rotateAround.getX(), rotateAround.getY(), rotateAround.getZ());
        for(Location loc : locations){
            double rotatedZ = Math.cos(rotatedegrees) * (loc.getZ() - loca.getZ()) - Math.sin(rotatedegrees) * (loc.getY() - loca.getY()) + loca.getZ();
            double rotatedY = Math.sin(rotatedegrees) * (loc.getZ() - loca.getZ()) + Math.cos(rotatedegrees) * (loc.getY() - loca.getY()) + loca.getY();
            newlocs.add(new Location(loca.getWorld(), loc.getX(), rotatedY, rotatedZ));
        }
        locs = newlocs;
        return newlocs;
    }

    /**
     * This method the global variables locs and base not be null.
     * Used to rotate a quadratic about the x axis.
     *
     * @param rotatedegrees - degrees the locations will be rotated
     * @return - list of locations rotated about the X axis
     */
    public List<Location> rotateAboutXAxis(float rotatedegrees){
        List<Location>  newlocs = new ArrayList<Location>();
        Location loca = new Location(base.getWorld(), base.getX(), base.getY(), base.getZ());
        for(Location loc : locs){
            double rotatedY = Math.cos(rotatedegrees) * (loc.getY() - loca.getY()) - Math.sin(rotatedegrees) * (loc.getZ()-loca.getZ()) + loca.getY();
            double rotatedZ = Math.sin(rotatedegrees) * (loc.getY() - loca.getY()) + Math.cos(rotatedegrees) * (loc.getZ() - loca.getZ()) + loca.getZ();
            newlocs.add(new Location(loca.getWorld(), loc.getX(), rotatedY, rotatedZ));
        }
        locs = newlocs;
        return newlocs;
    }

    /**
     * Used to create a new, arbitrary instance of the quadratic.
     *
     * @return - A new instance of the quadratic.
     */
    @Override
    public Quadratic clone(){
        return new Quadratic(this.a, this.b, this.c);
    }
}
