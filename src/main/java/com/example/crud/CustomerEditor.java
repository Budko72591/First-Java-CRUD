package com.example.crud;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

/**
 * A simple form for editing or creating a single {@link Customer}.
 * Handles Create, Update and Delete; reading is done by {@link MainView}.
 */
@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout implements KeyNotifier {

    private final CustomerRepository repository;

    /** The currently edited customer. */
    private Customer customer;

    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");

    private final Button save = new Button("Save", VaadinIcon.CHECK.create());
    private final Button cancel = new Button("Cancel");
    private final Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    private final HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private final Binder<Customer> binder = new Binder<>(Customer.class);
    private ChangeHandler changeHandler;

    public CustomerEditor(CustomerRepository repository) {
        this.repository = repository;

        add(firstName, lastName, actions);

        // bind fields to Customer properties by name
        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCustomer(customer));
        setVisible(false);
    }

    private void delete() {
        repository.delete(customer);
        changeHandler.onChange();
    }

    private void save() {
        repository.save(customer);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editCustomer(Customer c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // re-fetch from the DB so we edit a fresh copy
            customer = repository.findById(c.getId()).orElse(c);
        } else {
            customer = c;
        }
        cancel.setVisible(persisted);

        binder.setBean(customer);

        setVisible(true);
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // called when save or delete is clicked
        changeHandler = h;
    }
}
