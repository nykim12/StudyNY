package dbcp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder	//	Builder 패턴을 이용해서 ProductDTO 생성
@Getter
@Setter
public class ProductDTO {
	private long product_no;
	private String name;
	private Integer price;
	private String image;
}