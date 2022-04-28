package mybatis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDTO {
	private long productNo;
	private String name;
	private Integer price;
	private String image;
}