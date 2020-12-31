package pet.bean;

public class Comment {
	private int c_no;
	private int b_no;
	private String writer;
	private String content;
	private String created_at;
	
	@Override
	public String toString() {
		return "Comment [c_no=" + c_no + ", b_no=" + b_no + ", writer=" + writer + ", content=" + content
				+ ", created_at=" + created_at + "]";
	}
	
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
}
