package hello;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@SpringComponent
@UIScope
public class ComputeEditor extends VerticalLayout implements KeyNotifier {

    private final ComputeRepository repository ;

    /**
     * The currently edited compute
     */
    private Compute compute;

    /* Fields to edit properties in Customer entity */
    TextField a = new TextField("Value of A");
    TextField b = new TextField("Value of B");
    TextField c = new TextField("Value of C");
    TextField x = new TextField("Value of X");
    Date evtDate = new Date();

    /* Action buttons */
    // TODO why more code?
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Compute> binder = new Binder<>(Compute.class);
    private ComputeEditor.ChangeHandler changeHandler;

    @Autowired
    public ComputeEditor(ComputeRepository repository) {
        this.repository = repository;

        add(a,b,c,x, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCompute(compute));
        setVisible(false);
    }

    void delete() {
        repository.delete(compute);
        changeHandler.onChange();
    }

    void save() {
        repository.save(compute);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editCompute(Compute c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            compute = repository.findById(c.getId()).get();
        }
        else {
            compute = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(compute);

        setVisible(true);

        // Focus first name initially
        a.focus();
    }

    public void setChangeHandler(ComputeEditor.ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}
