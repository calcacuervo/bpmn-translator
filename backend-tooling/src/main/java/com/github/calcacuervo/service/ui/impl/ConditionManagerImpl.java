package com.github.calcacuervo.service.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.designer.expressioneditor.marshalling.ExpressionEditorMessageJSONMarshaller;
import org.jbpm.designer.expressioneditor.marshalling.ExpressionEditorMessageJSONUnmarshaller;
import org.jbpm.designer.expressioneditor.model.ConditionExpression;
import org.jbpm.designer.expressioneditor.model.ExpressionEditorMessage;
import org.jbpm.designer.expressioneditor.parser.ExpressionScriptGenerator;
import org.jbpm.designer.expressioneditor.server.ExpressionEditorErrors;
import org.jbpm.designer.expressioneditor.server.ExpressionEditorProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.calcacuervo.service.ui.ConditionManager;

public class ConditionManagerImpl implements ConditionManager {

	private static final Logger logger = LoggerFactory.getLogger(ExpressionEditorProcessor.class);

	private static final String GENERATE_COMMAND = "generateScript";

	@Override
	public String processCondition(String jsonExpression) {
		try {
			ExpressionEditorMessageJSONMarshaller marshaller = new ExpressionEditorMessageJSONMarshaller();
			ExpressionEditorMessageJSONUnmarshaller unmarshaller = new ExpressionEditorMessageJSONUnmarshaller();
			ExpressionEditorMessage requestMessage = null;
			ExpressionEditorMessage responseMessage = null;

			String message = jsonExpression;

			try {
				requestMessage = unmarshaller.unmarshall(message);
			} catch (Exception e) {
				System.out
						.println("It was not possible to unmarshall json message, request will be discarded. message: "
								+ e);
				return null;
			}

			responseMessage = doGenerateScript(requestMessage);

			if (responseMessage != null) {
				try {
					String jsonResponse = marshaller.marshall(responseMessage);
					if (logger.isDebugEnabled()) {
						logger.debug("sending response message: " + jsonResponse);
					}
					return jsonResponse;
				} catch (Exception e) {
					// unexpected error.
					logger.error("It was not possible to marshal the responseMessage: " + responseMessage, e);
				}
			}
		} catch (Exception e) {
			logger.error("Unexpected error during request processing.", e);
		}

		return null;
	}

	private ExpressionEditorMessage doGenerateScript(ExpressionEditorMessage requestMessage) {
		ExpressionEditorMessage responseMessage = new ExpressionEditorMessage();
		List<String> errors = new ArrayList<String>();
		ExpressionScriptGenerator generator = new ExpressionScriptGenerator();

		if (isValidMessageForCommand(GENERATE_COMMAND, requestMessage)) {
			ConditionExpression expression = requestMessage.getExpression();
			String script = generator.generateScript(expression, errors);

			if (script == null) {
				// process the errors.
				requestMessage.setErrorCode(ExpressionEditorErrors.SCRIPT_GENERATION_ERROR);
				responseMessage.setErrorMessage(concat(errors));
			}
			responseMessage.setScript(script);

		} else {
			responseMessage.setErrorCode(ExpressionEditorErrors.INVALID_MESSAGE_ERROR);
		}
		return responseMessage;
	}

	private boolean isValidMessageForCommand(String command, ExpressionEditorMessage message) {
		if (GENERATE_COMMAND.equals(command)) {
			if (message.getExpression() == null) {
				logger.error("No expression is present in message: " + message);
				return false;
			}
		}

		return true;
	}

	private String concat(List<String> values) {
		StringBuilder result = new StringBuilder();
		if (values == null || values.size() == 0)
			return result.toString();
		boolean first = true;
		for (String value : values) {
			if (!first) {
				result.append(", ");
			}
			result.append(value);
			first = false;
		}
		return result.toString();
	}

}
