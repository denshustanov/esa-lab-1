package org.example.entity;

public enum OrderStatus {

    DRAFT(Codes.DRAFT),
    CREATED(Codes.CREATED),
    PAID(Codes.PAID),
    SHIPMENT(Codes.SHIPMENT),

    DELIVERED(Codes.DELIVERED);

    private class Codes {
        private static final String DRAFT = "DRAFT";
        private static final String CREATED = "CREATED";
        private static final String PAID = "PAID";
        private static final String SHIPMENT = "SHIPMENT";
        private static final String DELIVERED = "DELIVERED";
    }

    OrderStatus(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return code;
    }

    public static OrderStatus decode(String code) {
        switch (code) {
            case Codes.DRAFT:
                return DRAFT;
            case Codes.CREATED:
                return CREATED;
            case Codes.PAID:
                return PAID;
            case Codes.SHIPMENT:
                return SHIPMENT;
            case Codes.DELIVERED:
                return DELIVERED;
            default:
                throw new IllegalArgumentException("Invalid status code");
        }
    }
}
