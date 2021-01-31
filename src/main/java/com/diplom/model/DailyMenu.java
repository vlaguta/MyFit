package com.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "daily_menu")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    // поле с датой
    private LocalDateTime data;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_daily_menu",
            joinColumns = @JoinColumn(name = "daily_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @OneToOne(optional = false, mappedBy = "dailyMenu")
    private Customer customer;
}

// кастомер айди в продукт дэили и убраль
// в дневнике должен быть айди пользователя и свзяать
// добавить в название и еще поля в таблицу дэйли меню
// хнранить аплоид файл спринг хранить файлы урлом (хранить название как ссылка на статический ресурс) в таблцик фото добавить нейм
// добавить ссылку на касмтомера в коммент И ПОСТ//
// комент кастомер айди или у поста кастомер айди//
// фото сет//
// ОneTOMany сет вместо листов//
// доделать энтите
// на саринг буте сделать пример с аплоидом файлом и созранением в статическую папку и вторая штука доставание этого файла
// сделать все страницы  хотя бы перечень


