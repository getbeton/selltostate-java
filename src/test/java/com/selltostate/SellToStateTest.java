package com.selltostate;

import java.util.HashMap;
import java.util.Map;

public class SellToStateTest {
    static int calls;

    public static void main(String[] args) throws Exception {
        calls = 0;
        SellToState sts = new SellToState("sts_test", "nope", null, (url, method, headers, body) -> {
            calls += 1;
            if (!url.equals("https://app.selltostate.com/api/v1/whoami")) throw new RuntimeException(url);
            if (!"sts_test".equals(headers.get("x-api-key"))) throw new RuntimeException("missing key");
            if (headers.containsKey("authorization")) throw new RuntimeException("bearer set for dashboard key");
            return new SellToState.Transport.Result(200, "{}");
        });
        sts.whoami();
        if (calls != 1) throw new RuntimeException("retry");

        calls = 0;
        SellToState pay = new SellToState("sts_test", null, null, (url, method, headers, body) -> {
            calls += 1;
            return new SellToState.Transport.Result(402, "{\"payment_link\":\"https://pay.example\",\"plan\":\"data-monthly\",\"machine_payment\":\"obj\"}");
        });
        try {
            pay.searchAwards(new HashMap<String, Object>());
            throw new RuntimeException("expected 402");
        } catch (SellToState.PaymentError err) {
            if (!"https://pay.example".equals(err.payment_link)) throw new RuntimeException(err.payment_link);
            if (!"data-monthly".equals(err.plan)) throw new RuntimeException(err.plan);
            if (err.machine_payment == null) throw new RuntimeException("machine_payment");
        }
        if (calls != 1) throw new RuntimeException("402 retry");
        System.out.println("java tests ok");
    }
}
