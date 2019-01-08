package com.bolsadeideas.springboot.backend.apirest.util;

import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(
                    list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }

    public static <T> Single<Page<T>> getPage(int page, int cant, List<T> data) {
        List<List<T>> l = chopped(data, cant);
        if (l.size() > page) {
            Page p = new Page();
            p.setContent(l.get(page));
            p.setFirst(page == 0);
            p.setLast(page == l.size() - 1);
            p.setTotalPages(l.size());
            p.setTotalElements(data.size());
            p.setSize(l.get(page).size());
            p.setNumbersOfElements(cant);
            p.setNumber(page);
            return Single.just(p);
        } else {
            return Single.error(new IndexOutOfBoundsException("No se pudo obtener página"));
        }
    }

}
