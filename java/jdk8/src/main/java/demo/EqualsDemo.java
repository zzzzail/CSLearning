package demo;

import java.util.Objects;

/**
 * @author zail
 * @date 2022/7/5
 */
public class EqualsDemo {
    
    public static void main(String[] args) {
        // equals 在什么情况下返回 false？
        Location a = new Location(1, 2);
        Location b = new Location(1, 2);
        System.out.println(a.equals(b));
    }
    
    static class Location {
        int x, y;
    
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
