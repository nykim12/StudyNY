package dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO {

	private static DataSource dataSource;
	private static ProductDAO productDAO = new ProductDAO();

	private ProductDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xe");
		} catch (NamingException e) {
			System.out.println("Resource name을 찾을 수 없습니다.");
		}
	}

	public static ProductDAO getInstance() {
		return productDAO;
	}

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ProductDTO> selectProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT PRODUCT_NO, NAME, PRICE, IMAGE FROM PRODUCT ORDER BY PRODUCT_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductDTO product = ProductDTO.builder()
						.product_no(rs.getLong("PRODUCT_NO"))
						.name(rs.getString("NAME"))
						.price(rs.getInt("PRICE"))
						.image(rs.getString("IMAGE"))
						.build();
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}

	public int insertProduct(ProductDTO product) throws Exception {

		int res = 0;

		con = dataSource.getConnection();
		sql = "INSERT INTO PRODUCT VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, product.getName());
		ps.setInt(2, product.getPrice());
		ps.setString(3, product.getImage());
		res = ps.executeUpdate();

		close(con, ps, null);

		return res;

	}
	
	public ProductDTO selectProductByNo(Long product_no) {
		ProductDTO product = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT PRODUCT_NO, NAME, PRICE, IMAGE FROM PRODUCT WHERE PRODUCT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, product_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				product = ProductDTO.builder()
						.product_no(rs.getLong("PRODUCT_NO"))
						.name(rs.getString("NAME"))
						.price(rs.getInt("PRICE"))
						.image(rs.getString("IMAGE"))
						.build();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps, rs);
		}
		return product;
	}
}