package com.example.tienda.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tienda.ModeloDAO.ConfiguracionRepositorio;
import com.example.tienda.ModeloDAO.DetalleRepositorio;
import com.example.tienda.ModeloVO.DetalleVO;
import com.example.tienda.ModeloVO.ProductoVO;

@Service
public class DetalleServicio {

	@Autowired
	private DetalleRepositorio detallerepositorio;

	@Autowired
	private ProductoServicio productoservicio;

	public List<DetalleVO> obtenerTodasLineasPedido(int id) {
		return detallerepositorio.findByPedidoId(id);
	}

	public List<DetalleVO> obtenerLineasPorProducto(int producto_id) {
		return detallerepositorio.findByProductoId(producto_id);
	}

	public Integer obtenerVentaProducto(int producto_id) {
	    List<DetalleVO> lineasTotales = obtenerLineasPorProducto(producto_id);
	    int totalUnidadesVendidas = 0;
	    
	    for(DetalleVO detalle : lineasTotales) {
	        totalUnidadesVendidas += detalle.getUnidades();
	    }

	    return totalUnidadesVendidas;
	}

	public void ActualizarStockCancelacion(List<DetalleVO> lista) {

		for (DetalleVO detalle : lista) {

			ProductoVO producto = productoservicio.selectProductoPorId(detalle.getProducto_id());

			producto.setStock(producto.getStock() + detalle.getUnidades());

			productoservicio.insertarProducto(producto);

		}
	}

}
