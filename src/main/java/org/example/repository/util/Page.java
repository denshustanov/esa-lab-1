package org.example.repository.util;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    long number;

    long size;

    long totalCount;

    long totalPages;

    List<T> data;
}
