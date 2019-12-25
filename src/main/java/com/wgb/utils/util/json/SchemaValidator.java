package com.wgb.utils.util.json;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.jdom2.JDOMException;

import java.io.IOException;
/**
 * @author INNERPEACE
 * @date 2019/11/6
 */
public class SchemaValidator {
	private final static JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

	public static boolean validateForJson(String json, String mainSchema) throws IOException {
		JsonNode mainNode = JsonLoader.fromString(mainSchema);
		JsonNode instanceNode = JsonLoader.fromString(json);
		JsonSchema schema = null;
		ProcessingReport processingReport = null;
		try {
			schema = factory.getJsonSchema(mainNode);
			processingReport = schema.validate(instanceNode);
		} catch (ProcessingException e){
			e.printStackTrace();
		}
		/*
		t*/
		return processingReport.isSuccess();
		// return true;
	}

	/**
	 *
	 * @param json 验证的Json
	 * @param mainSchema 验证的Schema
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	public static boolean validateForXml(String json, String mainSchema) throws IOException, JDOMException {

		JSONObject jsonObject = XmlUtil.xml2Json(json);
		return validateForJson(jsonObject.toJSONString(), mainSchema);
	}
}