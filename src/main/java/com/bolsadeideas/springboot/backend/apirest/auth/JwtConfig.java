package com.bolsadeideas.springboot.backend.apirest.auth;

public class JwtConfig {
    public static String SECRET_KEY = "alguna.clave.secreta.12345678";
    public static String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAzuTcl+VUz0A1XkIACp6HrpdfMMofBL6YeuDFKpU3eIbHk2yz\n" +
            "p/RsNtabQXNNZ+A9VcO1Gq+Muq+zZfReyD6RC4OaZnjjnHTmYdRazJhj04dAkcSN\n" +
            "G2fyXGmlbXyARCh475hB9b5XlLdv5nTEOvOXsBTUcAdGvAK1wMuGkJMKTiM7yGiG\n" +
            "IYp39bgY3SFdrjQu4/LlKBUXDuHewCtJFvlQ6ItjGDEKF8qkWaQYrIaQmzwQJ9Dh\n" +
            "DaT6piK9bTywPyfEf9V4d7Quq4rdItZT9bZXv8S4dFrn+J069MBueKBfkr2qpsTD\n" +
            "10lWqaJ1Psl1xpq/vg2JckysbT7+4fH4zIsQZwIDAQABAoIBAQC6zBGLJyi6cot8\n" +
            "zfRBoj542Py+DU+SNg9vqi76Mn1B2dapeZR/iQSHQp4sqtDxf5mBLNEABg32pBpC\n" +
            "bC+DdlH45lpqOK2wni8/lr5gSRqgalZXBfItE13UKCKPRsB8SVpfYMX0/WUPxctH\n" +
            "dJeTbkm/Vh80Aq3PpFa7ekbFk6zTPAbQ9CBnT7IIISVyFSIhpY28Uw+8R6cVsEfY\n" +
            "pPxwPfQQgd3LssOTDUL7yP7PxidjuHiLUUhmy40D6F/cHJPxYeUwS/T+tr0GK1QA\n" +
            "W7ZgwI0L9a2UYMbVDa9Cga+1WDzkORQU/BVlhSuUd9HC6Lgf7BpRR6MCj21iZEoa\n" +
            "s0tWmMbBAoGBAOwICCN9i9e+MFmeaHndWH+z+AdeKL929oLXvHoJIRAzhC0a6aWO\n" +
            "sBwm+OyUqx1lTaZ0bq33JswJqnAZRPbEzkPF/hKfMgii6lnN/F0VjBgGSFvNbjSG\n" +
            "RxE1bqIAmaAev7MyO/nmTebTRFh01192n5dz1uvjiH53SgGllKhWhgXHAoGBAOBl\n" +
            "xbn0eqIan+ChlVI/0MNCIVhyQB9GTt6GCqWeZe8OdvdIpb4cbaxS0qccvwq6Q8xm\n" +
            "mlqLcPLEDz1dmueen4ulImiQbToeETzeNoIUzzwBhJfCemrDWwvYscEEQVpUQfoc\n" +
            "gxosMQGnL5+zFkPFnJ1p6dQXdna/6HvPbnxcpiBhAoGBAOeHjWw6+BV+g/E1OWjp\n" +
            "XqmOQWwAylneeTuqIkFXeS5qSmrwbmcvi8Nh22Y/A4eHWt9AcV2HBnC5vo3Ny9+A\n" +
            "+JB0xKFBthPQs6+6Jqasv0rFxu7+me8+FLJlxot1qTzvTL6cNUcSyjH15aIbozUK\n" +
            "qMwpHpcwEDF2FIt/AE8M6NT7AoGAQguSQ6YCRh3h+oJ0aY3gyud22wpddxcBdZss\n" +
            "HQze4Zp3R1b5eVfyq6qA17CtReIGvG1ids3dw4YTGjOnuQ5F1RsYYoBHtbhNyIV6\n" +
            "s5SeXgHwXZ5e23d+H6F6x92Rwp1UAeJMb/KbxenIKY/TOMU70FTw/nydYFwhd/dY\n" +
            "hH/cYWECgYB5ZTAC8UxLSK85XrnXTtvNfiDI0KwYzuHJXEetjGdIYqXS1g6/4HkW\n" +
            "cFpp8gATR5yZvwn0fVM3iHsoVtgQRHIRS/1+Ow/CCzB5u8ynk1dKl7EhuTBQkt8h\n" +
            "qkN5Slv6sDjfkUrjoshlUkjGJ8QEI9fdXx4PL3h/O/JMua1aEikLug==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzuTcl+VUz0A1XkIACp6H\n" +
            "rpdfMMofBL6YeuDFKpU3eIbHk2yzp/RsNtabQXNNZ+A9VcO1Gq+Muq+zZfReyD6R\n" +
            "C4OaZnjjnHTmYdRazJhj04dAkcSNG2fyXGmlbXyARCh475hB9b5XlLdv5nTEOvOX\n" +
            "sBTUcAdGvAK1wMuGkJMKTiM7yGiGIYp39bgY3SFdrjQu4/LlKBUXDuHewCtJFvlQ\n" +
            "6ItjGDEKF8qkWaQYrIaQmzwQJ9DhDaT6piK9bTywPyfEf9V4d7Quq4rdItZT9bZX\n" +
            "v8S4dFrn+J069MBueKBfkr2qpsTD10lWqaJ1Psl1xpq/vg2JckysbT7+4fH4zIsQ\n" +
            "ZwIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
