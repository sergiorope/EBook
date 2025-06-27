package com.example.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tienda.ModeloDAO.ProductoRepositorio;
import com.example.tienda.ModeloVO.CategoriaVO;
// Asegúrate de importar el repositorio correcto
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.ProveedorVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductoServicio {

	@Autowired
	private ProductoRepositorio productoRepository;

	@Autowired
	private CategoriaServicio categoriaServicio;

	@Autowired
	private ProveedorServicio proveedorServicio;

	public List<ProductoVO> obtenerCatalogo() {
		return productoRepository.findAll();
	}

	public void insertarProducto(ProductoVO producto) {
		productoRepository.save(producto);
	}

	public void actualizarProducto(ProductoVO producto) {

		System.out.println(producto.getId());
		productoRepository.save(producto);

	}

	public ProductoVO selectProductoPorId(int id) {
		return productoRepository.findById(id).get();
	}

	public Map<Integer, String> MostrarListaCategoria() {
		List<ProductoVO> catalogo = obtenerCatalogo();

		Map<Integer, String> productoporcategoria = new HashMap<>();

		for (ProductoVO producto : catalogo) {

			CategoriaVO categoria = categoriaServicio.obtenerCategoriaId(producto.getCategoria_id());

			productoporcategoria.put(producto.getId(), categoria.getNombre());
		}
		return productoporcategoria;

	}

	public Map<Integer, String> MostrarListaProveedor() {
		List<ProductoVO> catalogo = obtenerCatalogo();

		Map<Integer, String> productoporproveedor = new HashMap<>();

		for (ProductoVO producto : catalogo) {

			ProveedorVO proveedor = proveedorServicio.obtenerProveedorPorId(producto.getProveedor_id());

			productoporproveedor.put(producto.getId(), proveedor.getNombre());
		}
		return productoporproveedor;

	}

	public void importarProductosDesdeExcel(String rutaArchivo) {
		try {
			Workbook workbook = Workbook.getWorkbook(new File(rutaArchivo));
			Sheet hoja = workbook.getSheet(0);

			for (int fila = 0; fila < hoja.getRows(); fila++) {
				Cell[] celdas = hoja.getRow(fila);

				int categoriaId = Integer.parseInt(celdas[0].getContents());
				String nombre = celdas[1].getContents();
				String descripcion = celdas[2].getContents();
				double precio = Double.parseDouble(celdas[3].getContents());
				double impuesto = Double.parseDouble(celdas[4].getContents());
				int stock = Integer.parseInt(celdas[5].getContents());
				int baja = Integer.parseInt(celdas[6].getContents());
				String imagenUrl = celdas[7].getContents();

				ProductoVO producto = new ProductoVO();

				producto.setCategoria_id(categoriaId);
				producto.setNombre(nombre);
				producto.setDescripcion(descripcion);
				producto.setPrecio(precio);
				producto.setImpuesto(impuesto);
				producto.setStock(stock);
				producto.setBaja(baja);
				producto.setImagen(imagenUrl);

				insertarProducto(producto);

				System.out.println("Producto importado: " + nombre);
			}

			workbook.close();
		} catch (IOException | BiffException e) {
			e.printStackTrace();
		}
	}

	public void exportarProductosAExcel(String rutaArchivo) {
		try {
			List<ProductoVO> listaProductos = obtenerCatalogo();

			// Crear un nuevo archivo Excel
			WritableWorkbook workbook = Workbook.createWorkbook(new File(rutaArchivo));
			WritableSheet hoja = workbook.createSheet("Productos", 0);

			String[] encabezados = { "categoria_id", "nombre", "descripcion", "precio", "impuesto", "stock", "baja",
					"imagen" };
			for (int i = 0; i < encabezados.length; i++) {
				Label etiqueta = new Label(i, 0, encabezados[i]);
				hoja.addCell(etiqueta);
			}

			for (int i = 0; i < listaProductos.size(); i++) {
				ProductoVO producto = listaProductos.get(i);
				hoja.addCell(new Label(0, i + 1, String.valueOf(producto.getCategoria_id())));
				hoja.addCell(new Label(1, i + 1, producto.getNombre()));
				hoja.addCell(new Label(2, i + 1, producto.getDescripcion()));
				hoja.addCell(new Label(3, i + 1, String.valueOf(producto.getPrecio())));
				hoja.addCell(new Label(4, i + 1, String.valueOf(producto.getImpuesto())));
				hoja.addCell(new Label(5, i + 1, String.valueOf(producto.getStock())));
				hoja.addCell(new Label(6, i + 1, String.valueOf(producto.getBaja())));
				hoja.addCell(new Label(7, i + 1, producto.getImagen()));
			}

			workbook.write();
			workbook.close();

			System.out.println("Productos exportados correctamente a Excel.");
			String directorioTrabajo = System.getProperty("user.dir");
			System.out.println("Directorio de trabajo actual: " + directorioTrabajo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}
