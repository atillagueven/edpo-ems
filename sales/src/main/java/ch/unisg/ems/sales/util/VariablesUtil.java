package ch.unisg.ems.sales.util;

import ch.unisg.ems.sales.dto.OfferDto;
import lombok.experimental.UtilityClass;
import ch.unisg.ems.sales.dto.CamundaOfferDto;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class VariablesUtil {

    public <T> Map<String, Object> toVariableMap(T object) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> variables = new HashMap<>();
        BeanInfo info = Introspector.getBeanInfo(object.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null && !pd.getName().equals("class")) {
                Object value = reader.invoke(object);
                if(value != null) {
                    variables.put(pd.getName(), reader.invoke(object));
                }
            }
        }
        return  variables;
    }

    public <T> CamundaOfferDto buildCamudaOfferDto(String businessKey, Map<String, Object> variablesMap){
        return CamundaOfferDto.builder().correlationId(businessKey)
                .dto(OfferDto.builder().customerId((String) variablesMap.get("requester"))
                        .accepted((Boolean) variablesMap.get("preApproved"))
                        .build()).build();
    }

}
