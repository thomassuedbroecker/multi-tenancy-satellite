package com.ibm.articlesdata;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.annotation.PostConstruct;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Security
import org.jboss.resteasy.annotations.cache.NoCache;
// import javax.annotation.security.RolesAllowed;

// Token
import org.eclipse.microprofile.jwt.JsonWebToken;
import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.RefreshToken;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/")
public class ArticleResourceData {

    @Inject
    @IdToken
    JsonWebToken idToken;
    @Inject
    JsonWebToken accessToken;
    @Inject
    RefreshToken refreshToken;
    
    private Set<Article> articles = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    // Tenant
    @ConfigProperty(name = "cns.tenant_A")
    private String tenant_A;
    @ConfigProperty(name = "cns.tenant_B")
    private String tenant_B;

    @GET
    @Path("/articlesA")
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public Set<Article> getArticlesA() {
        System.out.println("-->log: com.ibm.articles.ArticlesResource.getArticlesA");
        Object issuer = this.accessToken.getClaim("iss");
        Object scope = this.accessToken.getClaim("scope");
        System.out.println("-->log: com.ibm.articles.ArticlesResource.getArticles issuer A: " + issuer.toString());
        System.out.println("-->log: com.ibm.articles.ArticlesResource.getArticles scope A: " + scope.toString());
        System.out.println("-->log: com.ibm.articles.ArticlesResource.getArticles articles A: " + articles.toArray().toString());
        return articles;
    }

    @GET
    @Path("/articlesB")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticated
    //@RolesAllowed("user")
    @NoCache
    public Set<Article> getArticlesB() {
        System.out.println("-->log: com.ibm.articles.ArticlesResource.getArticlesB");
        Object issuer = this.accessToken.getClaim("iss");
        System.out.println("-->log: com.ibm.articles.ArticlesResource.getArticles issuer B: " + issuer.toString());
        System.out.println("-->log: com.ibm.articles.ArticlesResource.getArticles articles B: " + articles.toArray().toString());
        return articles;
    }

    @PostConstruct
    void addArticles() {
        System.out.println("-->log: com.ibm.articles.ArticleResource.addArticles");
        addSampleArticles();
    }

    private void addArticle(String title, String url, String author) {
        Article article = new Article();
        article.title = title;
        article.url = url;
        article.authorName = author;
        articles.add(article);
    }

    private void addSampleArticles() {
        System.out.println("-->log: com.ibm.articles.ArticlesResource.addSampleArticles");
        
        // 1. Select tenant  =================================================
        String tenant =  tenantJSONWebToken();
        System.out.println("-->log: com.ibm.articles.ArticleResourceData.addSampleArticles tenant: " + tenant);
               
        if ("tenantA".equals(tenant)){
            addArticle("Blue Cloud Mirror — (Don’t) Open The Doors! (at blog.de - sample data)", "https://haralduebele.github.io/2019/02/17/blue-cloud-mirror-dont-open-the-doors/" ,"Harald Uebele");
        } else {
            addArticle("App ID vs. Keycloak ! (at blog.de - sample data)", "https://haralduebele.github.io/2019/02/17/blue-cloud-mirror-dont-open-the-doors/", "Dr. Nobody");     
        }
               
        if ("tenantB".equals(tenant)){
            addArticle("Blue Cloud Mirror — (Don’t) Open The Doors! (at blog.com - sample data)", "https://haralduebele.github.io/2019/02/17/blue-cloud-mirror-dont-open-the-doors/", "Harald Uebele");
        } 
    }

    private String tenantJSONWebToken(){
        try {
            Object issuer = this.accessToken.getClaim("iss");
            System.out.println("-->log: com.ibm.articles.ArticlesResourceData.tenantJSONWebToken issuer: " + issuer.toString());

            String[] parts = issuer.toString().split("/");
            System.out.println("-->log: com.ibm.articles.ArticlesResourceData.log part[5]: " + parts[5]);

            if (parts.length == 0) {
                return "empty";
            }
    
            if ("tenantA".equals(parts[5])) {
                return "tenantA";
            }

            if ("tenantB".equals(parts[5])) {
                return "tenantB";
            }
    
            return "tenant not known";

        } catch ( Exception e ) {
            System.out.println("-->log: com.ibm.articles.ArticlesResourceData.log Exception: " + e.toString());
            return "error";
        }
    }
}