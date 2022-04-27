package dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	private static ProductDAO productDAO = new ProductDAO();

	private ProductDAO() {}

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
			con = MyConnection.getInstance().getConnection();
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

		con = MyConnection.getInstance().getConnection();
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
			con = MyConnection.getInstance().getConnection();
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
	
	public int deleteProduct(Long product_no) {
		
		int res = 0;

		try {
			con = MyConnection.getInstance().getConnection();
			sql = "DELETE FROM PRODUCT WHERE PRODUCT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, product_no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}

}