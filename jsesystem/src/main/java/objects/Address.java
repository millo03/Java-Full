package objects;

public class Address {
//  Constants
	public static final String CITYNAME = "No CityName";
	public static final String DISTRICTNAME = "No DistrictName";
	public static final String STREETNAME = "No StreetName";

//	Object's properties
	private String cityName;
	private String districtName;
	private String streetName;

	public Address() {
		this(Address.CITYNAME, Address.DISTRICTNAME, Address.STREETNAME);
	}

	public Address(String cityName, String districtName, String streetName) {
		super();
		this.cityName = cityName;
		this.districtName = districtName;
		this.streetName = streetName;
	}

	public Address(Address address) {
		this(address.getCityName(), address.getDistrictName(), address.getStreetName());
	}
	// Nguyên lí: nó phải có thằng tồn tại trước và thằng tồn tại sau
//	address là khởi tạo trước còn Address chưa có tên, truy cập thông qua đối tượng this, đang lấy giá trị addr gán cho Address(tên hàm)
// addr phải là đối tượng đã được tồn tại trong bộ nhớ, tức là constructor thứ nhất và thứ hai phải đã được khởi tạo
	//Khi quy hoach thafnh oop thì thứ tự viết không quan trọng
//	db loai 3: tham số truyền vào chính là lớp đối tượng mà đang sử dụng
	public String getCityName() {
		return cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [cityName = ");
		builder.append(cityName);
		builder.append(", districtName = ");
		builder.append(districtName);
		builder.append(", streetName = ");
		builder.append(streetName);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println();
	}
}
