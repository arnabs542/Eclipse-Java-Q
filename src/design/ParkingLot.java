package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingLot {
	private Level[] levels;
	
	public ParkingLot(int numOfLevels, int numSpotsPerLevel) {
		levels = new Level[numOfLevels];
		for (int level = 0; level < numOfLevels; level++) {
			levels[level] = new Level(numSpotsPerLevel);
		}
	}
	
	boolean hasSpot(Vehicle v) {
		for (Level level : levels) {
			if (level.hasSpot(v)) {
				return true;
			}
		}
		return false;
	}
	
	boolean park(Vehicle v) {
		for (Level level : levels) {
			if (level.park(v)) {
				return true;
			}
		}
		return false;
	}
	
	boolean leave(Vehicle v) {
		for (Level level : levels) {
			if (level.leave(v)) {
				return true;
			}
		}
		return false;
	}
}

class Level {
	private final List<ParkingSpot> spots;
	
	Level(int numOfSpots) {
		
		// How to let the Level layout is fixed and will never change
		List<ParkingSpot> list = new ArrayList<>(numOfSpots);
		int num = 0;
		for (; num < numOfSpots / 2; num++) {
			list.add(new ParkingSpot(VehicleSize.Compact));
		}
		for (; num < numOfSpots; num++) {
			list.add(new ParkingSpot(VehicleSize.Large));
		}				
		spots = Collections.unmodifiableList(list);
	}
	
	boolean hasSpot(Vehicle v) {
		for (ParkingSpot spot : spots) {
			if (spot.fit(v)) {
				return true;
			}
		}
		return false;
	}
	
	boolean park(Vehicle v) {
		for (ParkingSpot spot : spots) {
			if (spot.fit(v)) {
				spot.park(v);
				return true;
			}
		}
		return false;
	}
	
	boolean leave(Vehicle v) {
		for (ParkingSpot spot : spots) {
			if (spot.getVehicle() == v) {
				spot.leave();
				return true;
			}
		}
		return false;
	}
}

class ParkingSpot {
	private final VehicleSize size;
	private Vehicle currentVehicle; // null is no vehicle is parked inside
	
	ParkingSpot(VehicleSize size) {
		this.size = size;
	}
	
	boolean fit(Vehicle v) {
		/* Three ways to check size. Which is better:
		 * 
		 * 1. if - else
		 * 2. enum ordinal
		 * 3. add a size attribute in enum VehicleSize and use it for comparison
		 * 
		 */
		return currentVehicle == null && size.getSize() >= v.getSize().getSize();
	}
	
	void park(Vehicle v) {
		currentVehicle = v;
	}
	
	void leave() {
		currentVehicle = null;
	}
	
	Vehicle getVehicle() {
		return currentVehicle;
	}
}

enum VehicleSize {
	Compact(1), Large(2);
	
	private final int size;
	
	VehicleSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}

abstract class Vehicle {
//	private final String plate = ""; // ? 
//	private final VehicleSize size = VehicleSize.Compact; // ?
	public abstract VehicleSize getSize();
}

class Car extends Vehicle {
	@Override
	public VehicleSize getSize() {
		return VehicleSize.Compact;
	}
}

class Truck extends Vehicle {
	@Override
	public VehicleSize getSize() {
		return VehicleSize.Large;
	}
}

class Test {
	public static void main(String[] args) {
		ParkingLot lot = new ParkingLot(4, 10);
		List<Vehicle> list = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
			list.add(v);
			boolean hasSpot = lot.hasSpot(v);
			if (i < 40) {
				assert hasSpot;
				assert lot.park(v);
			} else {
				assert !hasSpot;
				assert !lot.park(v);
			}
		}
		assert list.size() == 50;
		int i = 0;
		for (Vehicle v: list) {
			assert i >= 40 || lot.leave(v);
			i++;
		}
	}
}

