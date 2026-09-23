// Generated from OpenAPI. Do not edit methods by hand.
package com.selltostate;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SellToState {
    public static final String DEFAULT_BASE_URL = "https://app.selltostate.com";
    private final String baseUrl;
    private final String apiKey;
    private final String clientId;
    private final Transport transport;

    public SellToState(String apiKey, String clientId, String baseUrl, Transport transport) {
        String envKey = System.getenv("STS_API_KEY");
        String envUrl = System.getenv("STS_API_URL");
        this.apiKey = apiKey != null ? apiKey : envKey;
        this.clientId = clientId;
        String raw = baseUrl != null ? baseUrl : (envUrl != null ? envUrl : DEFAULT_BASE_URL);
        this.baseUrl = raw.replaceAll("/+$", "");
        this.transport = transport;
    }

    public Object request(String method, String path, Map<String, Object> args, String[] pathParams) throws Exception {
        String urlPath = path;
        for (String name : pathParams) {
            Object value = args.get(name);
            if (value == null || "".equals(value)) throw new IllegalArgumentException("missing path param: " + name);
            urlPath = urlPath.replace("{" + name + "}", URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8));
        }
        StringBuilder qs = new StringBuilder();
        for (Map.Entry<String, Object> entry : args.entrySet()) {
            String key = entry.getKey();
            if ("body".equals(key) || contains(pathParams, key)) continue;
            Object value = entry.getValue();
            if (value == null || "".equals(value)) continue;
            if (qs.length() > 0) qs.append("&");
            qs.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
            qs.append("=");
            qs.append(URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8));
        }
        String url = baseUrl + urlPath + (qs.length() > 0 ? "?" + qs : "");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("accept", "application/json");
        if (apiKey != null && !apiKey.isEmpty()) headers.put("x-api-key", apiKey);
        else if (clientId != null && !clientId.isEmpty()) headers.put("authorization", "Bearer client:" + clientId);
        String body = null;
        if (args.get("body") != null && !"GET".equals(method)) {
            headers.put("content-type", "application/json");
            body = String.valueOf(args.get("body"));
        }
        int status;
        String text;
        if (transport != null) {
            Transport.Result result = transport.exchange(url, method, headers, body);
            status = result.status;
            text = result.text;
        } else {
            HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url)).method(
                method,
                body == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(body)
            );
            for (Map.Entry<String, String> header : headers.entrySet()) builder.header(header.getKey(), header.getValue());
            HttpResponse<String> res = HttpClient.newHttpClient().send(builder.build(), HttpResponse.BodyHandlers.ofString());
            status = res.statusCode();
            text = res.body();
        }
        if (status == 401) throw new AuthError(text);
        if (status == 402) throw new PaymentError(text);
        if (status == 429) throw new RateLimitError(text);
        if (status >= 400) throw new RuntimeException("HTTP " + status);
        return text;
    }

    private static boolean contains(String[] items, String key) {
        for (String item : items) if (item.equals(key)) return true;
        return false;
    }

    public Object searchAwards() throws Exception {
        return searchAwards(new HashMap<String, Object>());
    }

    public Object searchAwards(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/awards", args, new String[]{});
    }

    public Object exportAwardsCsv() throws Exception {
        return exportAwardsCsv(new HashMap<String, Object>());
    }

    public Object exportAwardsCsv(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/awards.csv", args, new String[]{});
    }

    public Object getPrice() throws Exception {
        return getPrice(new HashMap<String, Object>());
    }

    public Object getPrice(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/price", args, new String[]{});
    }

    public Object payAutonomously() throws Exception {
        return payAutonomously(new HashMap<String, Object>());
    }

    public Object payAutonomously(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("POST", "/api/v1/pay", args, new String[]{});
    }

    public Object registerClient() throws Exception {
        return registerClient(new HashMap<String, Object>());
    }

    public Object registerClient(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("POST", "/api/oidc/register", args, new String[]{});
    }

    public Object mcp() throws Exception {
        return mcp(new HashMap<String, Object>());
    }

    public Object mcp(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("POST", "/api/mcp", args, new String[]{});
    }

    public Object protectedResourceMetadata() throws Exception {
        return protectedResourceMetadata(new HashMap<String, Object>());
    }

    public Object protectedResourceMetadata(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/.well-known/oauth-protected-resource", args, new String[]{});
    }

    public Object authorizationServerMetadata() throws Exception {
        return authorizationServerMetadata(new HashMap<String, Object>());
    }

    public Object authorizationServerMetadata(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/.well-known/oauth-authorization-server", args, new String[]{});
    }

    public Object whoami() throws Exception {
        return whoami(new HashMap<String, Object>());
    }

    public Object whoami(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/whoami", args, new String[]{});
    }

    public Object search() throws Exception {
        return search(new HashMap<String, Object>());
    }

    public Object search(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/search", args, new String[]{});
    }

    public Object listBuyers() throws Exception {
        return listBuyers(new HashMap<String, Object>());
    }

    public Object listBuyers(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/buyers", args, new String[]{});
    }

    public Object getBuyer(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/buyers/{slug}", args, new String[]{"slug"});
    }

    public Object listSuppliers() throws Exception {
        return listSuppliers(new HashMap<String, Object>());
    }

    public Object listSuppliers(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/suppliers", args, new String[]{});
    }

    public Object getSupplier(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/suppliers/{slug}", args, new String[]{"slug"});
    }

    public Object listSectors() throws Exception {
        return listSectors(new HashMap<String, Object>());
    }

    public Object listSectors(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/sectors", args, new String[]{});
    }

    public Object getSector(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/sectors/{slug}", args, new String[]{"slug"});
    }

    public Object getTender(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/tenders/{id}", args, new String[]{"id"});
    }

    public Object getLists() throws Exception {
        return getLists(new HashMap<String, Object>());
    }

    public Object getLists(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/lists", args, new String[]{});
    }

    public Object createList() throws Exception {
        return createList(new HashMap<String, Object>());
    }

    public Object createList(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("POST", "/api/v1/lists", args, new String[]{});
    }

    public Object getList(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/lists/{id}", args, new String[]{"id"});
    }

    public Object renameList(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("PATCH", "/api/v1/lists/{id}", args, new String[]{"id"});
    }

    public Object deleteList(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("DELETE", "/api/v1/lists/{id}", args, new String[]{"id"});
    }

    public Object addToList(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("POST", "/api/v1/lists/{id}/items", args, new String[]{"id"});
    }

    public Object removeFromList(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("DELETE", "/api/v1/lists/{id}/items", args, new String[]{"id"});
    }

    public Object getSavedSearches() throws Exception {
        return getSavedSearches(new HashMap<String, Object>());
    }

    public Object getSavedSearches(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/saved-searches", args, new String[]{});
    }

    public Object saveSearch() throws Exception {
        return saveSearch(new HashMap<String, Object>());
    }

    public Object saveSearch(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("POST", "/api/v1/saved-searches", args, new String[]{});
    }

    public Object getSavedSearch(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("GET", "/api/v1/saved-searches/{id}", args, new String[]{"id"});
    }

    public Object renameSavedSearch(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("PATCH", "/api/v1/saved-searches/{id}", args, new String[]{"id"});
    }

    public Object deleteSavedSearch(Map<String, Object> args) throws Exception {
        if (args == null) args = new HashMap<String, Object>();
        return request("DELETE", "/api/v1/saved-searches/{id}", args, new String[]{"id"});
    }

    public interface Transport {
        Result exchange(String url, String method, Map<String, String> headers, String body);
        final class Result {
            public final int status;
            public final String text;
            public Result(int status, String text) { this.status = status; this.text = text; }
        }
    }

    public static class AuthError extends Exception {
        public final int status = 401;
        public final String body;
        public AuthError(String body) { super("unauthorized"); this.body = body; }
    }

    public static class PaymentError extends Exception {
        public final int status = 402;
        public final String body;
        public final String payment_link;
        public final String plan;
        public final String machine_payment;
        public PaymentError(String body) {
            super("payment_required");
            this.body = body == null ? "" : body;
            this.payment_link = field(this.body, "payment_link");
            this.plan = field(this.body, "plan");
            this.machine_payment = field(this.body, "machine_payment");
        }
        private static String field(String json, String name) {
            String needle = "\"" + name + "\":\"";
            int at = json.indexOf(needle);
            if (at < 0) return null;
            int start = at + needle.length();
            int end = json.indexOf("\"", start);
            return end < 0 ? null : json.substring(start, end);
        }
    }

    public static class RateLimitError extends Exception {
        public final int status = 429;
        public final String body;
        public RateLimitError(String body) { super("rate_limited"); this.body = body; }
    }
}
