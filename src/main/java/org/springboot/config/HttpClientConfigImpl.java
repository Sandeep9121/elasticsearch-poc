package org.springboot.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.File;

@Configuration
public class HttpClientConfigImpl implements RestClientBuilder.HttpClientConfigCallback {
    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
        try {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials passwordCredentials = new UsernamePasswordCredentials("elastic", "XYirPzGms7bR9F5+xjv-");
            credentialsProvider.setCredentials(AuthScope.ANY, passwordCredentials);
            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            String trustStoreLocation = "";
            File trustStoreLocationFile = new File(trustStoreLocation);
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustStoreLocationFile, "password".toCharArray());
            SSLContext sslContext = sslContextBuilder.build();
            httpAsyncClientBuilder.setSSLContext(sslContext);

        } catch (Exception e) {

        }
        return httpAsyncClientBuilder;
    }
}
