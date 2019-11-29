package com.takahiro310.jasper

import net.sf.jasperreports.engine.JREmptyDataSource
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.io.ClassPathResource
import java.nio.file.Paths

//@SpringBootApplication
class JasperApplication

fun main(args: Array<String>) {
	try {
		val absolutePath = ClassPathResource("report.jrxml").file.absolutePath
		val jasperReport = JasperCompileManager.compileReport(absolutePath)
		val params = HashMap<String, Any>()
		params.put("text_field", "おはようございます！今日もいい天気ですね。")
		val print = JasperFillManager.fillReport(jasperReport, params, JREmptyDataSource())
		val output = Paths.get("/tmp/sample.pdf")
		JasperExportManager.exportReportToPdfFile(print, output.toFile().absolutePath)
	} catch (e: Exception) {
		println(e.message)
	}
}
