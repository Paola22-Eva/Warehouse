/**
 * Абстрактный базовый класс для всех типов накладных на складе.
 * Определяет общую структуру документа: номер, дату создания,
 * статус и список позиций. Содержит позиции в композиции:
 * DocumentItem не может существовать без Document.
 */
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public abstract class Document {
    // ... атрибуты ...
    protected int id;
    private String number;
    private Date createdAt;
    private DocumentStatus status;
    private final List<DocumentItem> items = new ArrayList<>();

    /**
     * Создаёт новый документ.
     * @param id уникальный идентификатор документа
     * @param number номер накладной
     * @param createdAt дата создания документа
     * @param status начальный статус документа
     */
    public Document(int id, String number, Date createdAt, DocumentStatus status) {
        this.id = id;
        this.number = number;
        this.createdAt = createdAt;
        this.status = status;
    }

    // ... геттеры и сеттеры  ...
    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public List<DocumentItem> getItems() {
        return items;
    }

    /**
     * Добавляет позицию в документ.
     * @param item позиция для добавления
     */
    public void addItem(DocumentItem item) {
        items.add(item);
    }

    /**
     * Вычисляет общую сумму по всем позициям документа.
     * @return сумма стоимостей всех позиций
     */
    public double getTotalAmount() {
        double total = 0;
        for (DocumentItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Подтверждает документ, переводя его в статус CONFIRMED.
     * Выбрасывает исключение, если документ не содержит ни одной позиции
     * (защита инварианта кратности 1..*).
     * @throws IllegalStateException если список позиций пуст
     */
    public void confirm() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Нельзя подтвердить пустой документ. Добавьте хотя бы одну позицию.");
        }
        this.status = DocumentStatus.CONFIRMED;
    }


    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}