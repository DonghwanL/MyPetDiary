package pet.bean;

	public class Member {
		private String id; // ID
		private String name; // 이름
		private String nickname; // 닉네임
		private String password; // PASSWORD
		private String email; // EMAIL
		private String phone; // 핸드폰 번호
		private String zipcode; // 우편 번호
		private String address1; // 주소 1
		private String address2; // 주소 2
		private int mpoint; // 회원 포인트
		private int mlevel; // 회원 등급 (기본 1, 우수 2, 특급 3)
		private String created_at; // 가입 일자
		private int status; // 회원 상태 (가입 0 , 탈퇴 1)
		
		public Member() {}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public int getMpoint() {
			return mpoint;
		}

		public void setMpoint(int mpoint) {
			this.mpoint = mpoint;
		}

		public int getMlevel() {
			return mlevel;
		}

		public void setMlevel(int mlevel) {
			this.mlevel = mlevel;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "Member [id=" + id + ", name=" + name + ", nickname=" + nickname + ", password=" + password
					+ ", email=" + email + ", phone=" + phone + ", zipcode=" + zipcode + ", address1=" + address1
					+ ", address2=" + address2 + ", mpoint=" + mpoint + ", mlevel=" + mlevel + ", created_at="
					+ created_at + ", status=" + status + "]";
		}

	}
