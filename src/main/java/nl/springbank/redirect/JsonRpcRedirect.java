package nl.springbank.redirect;

import com.google.gson.Gson;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.controllers.TestController;
import nl.springbank.controllers.account.AccountController;
import nl.springbank.controllers.authentication.Authentication;
import nl.springbank.controllers.bankaccount.BankAccountController;
import nl.springbank.helper.JsonRpcRequest;
import org.apache.hc.client5.http.impl.sync.CloseableHttpClient;
import org.apache.hc.client5.http.impl.sync.HttpClients;
import org.apache.hc.client5.http.methods.HttpPost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.entity.ContentType;
import org.apache.hc.core5.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * Ugly hack to redirect request to the corresponding JSON RPC endpoint.
 *
 * @author Tristan de Boer.
 */
@Controller
public class JsonRpcRedirect {
    private static final String DEFAULT_URL = "http://localhost:8080";

    private CloseableHttpClient httpclient = HttpClients.createDefault();

    @Autowired
    private TestController testController;

    @Autowired
    private Authentication authentication;

    @Autowired
    private AccountController accountController;

    @Autowired
    private BankAccountController bankAccountController;

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public String redirect(@RequestBody String json) {
        Gson gson = new Gson();
        JsonRpcRequest jsonRequest = gson.fromJson(json, JsonRpcRequest.class);
        String result = null;

        ClassPathScanningCandidateComponentProvider provider = new
                ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(AutoJsonRpcServiceImpl.class));
        Set<BeanDefinition> beans = provider.findCandidateComponents("nl.springbank");
        for (BeanDefinition bd : beans) {
            Class<?> bean = null;
            try {
                bean = Class.forName(bd.getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for (Method method : bean.getMethods()) {
                if (method.getName().equals(jsonRequest.getMethod())) {
                    // Method found in bd
                    for (Field field : getClass().getDeclaredFields()) {
                        if (field.getType().isAssignableFrom(bean)) {
                            // Found the service belonging to bd
                            field.setAccessible(true);
                            try {
                                Object service = field.get(this);
                                Class<?> returnType = method.getReturnType();

                                JsonRpcService annotation = AnnotationUtils.findAnnotation(service.getClass(),
                                        JsonRpcService.class);
                                HttpPost httpPost = new HttpPost(DEFAULT_URL + annotation.value());
                                StringEntity msg = new StringEntity(json, ContentType.create("application/json",
                                        "UTF-8"));
                                httpPost.setEntity(msg);
                                HttpResponse response = httpclient.execute(httpPost);

                                BufferedReader reader = new BufferedReader(new InputStreamReader(
                                        (response.getEntity().getContent())));
                                String out; StringBuilder output = new StringBuilder();
                                while ((out = reader.readLine()) != null) {
                                    output.append(out);
                                }

                                result = output.toString();
                            } catch (IllegalAccessException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

}
