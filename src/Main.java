import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // 1. Создаем склад
        Warehouse warehouse = new Warehouse(1, "Главный склад", "г. Москва, ул. Складская, 1");
        System.out.println("=== СОЗДАН СКЛАД ===");
        System.out.println(warehouse);

        // 2. Создаем ячейки (агрегация - создаются независимо от склада)
        Cell cell1 = new Cell(1, "A-01-1", 100, 0);
        Cell cell2 = new Cell(2, "A-01-2", 150, 0);
        warehouse.addCell(cell1);
        warehouse.addCell(cell2);

        System.out.println("\n=== ЯЧЕЙКИ НА СКЛАДЕ ===");
        System.out.println("Ячейка 1 доступна: " + cell1.isAvailable());
        System.out.println("Общая вместимость склада: " + warehouse.getTotalCapacity());

        // 3. Создаем товары (каталог)
        Product laptop = new Product(1, "Ноутбук ASUS", "ASU-001", 75000.0);
        Product mouse = new Product(2, "Мышь беспроводная", "MOU-045", 1500.0);

        System.out.println("\n=== ТОВАРЫ В КАТАЛОГЕ ===");
        System.out.println(laptop);
        System.out.println(mouse);
        System.out.println("Ноутбук в наличии: " + laptop.isInStock());

        // 4. Размещаем товары в ячейках (композиция - создаются через ячейку)
        StockItem stockLaptop = cell1.addStockItem(10, new Date(), laptop);
        StockItem stockMouse = cell2.addStockItem(50, new Date(), mouse);

        System.out.println("\n=== ТОВАРЫ РАЗМЕЩЕНЫ В ЯЧЕЙКАХ ===");
        System.out.println("Партия ноутбуков: " + stockLaptop);
        System.out.println("Стоимость партии: " + stockLaptop.getTotalValue() + " руб.");
        System.out.println("Ячейка 1 доступна после размещения: " + cell1.isAvailable());

        // 5. Создаем кладовщика
        WarehouseWorker worker = new WarehouseWorker(1, "Иванов П.С.", "Кладовщик");
        System.out.println("\n=== КЛАДОВЩИК ===");
        System.out.println(worker);

        // 6. Создаем документ поступления
        System.out.println("\n=== ДОКУМЕНТ ПОСТУПЛЕНИЯ ===");
        ReceiptDocument receipt = new ReceiptDocument(
                1, "REC-2026-001", new Date(), DocumentStatus.DRAFT, "ООО Поставщик"
        );
        receipt.addProduct(laptop, 5);
        receipt.addProduct(mouse, 20);

        System.out.println("Позиций в документе: " + receipt.getItems().size());
        System.out.println("Общая сумма поступления: " + receipt.getTotalAmount() + " руб.");
        System.out.println("Остаток ноутбуков на складе: " + laptop.getStock());

        // 7. Кладовщик обрабатывает документ
        System.out.println("\n=== ОБРАБОТКА ДОКУМЕНТА ===");
        worker.processDocument(receipt);
        System.out.println("Статус после обработки: " + receipt.getStatus());

        // 8. Создаем документ отгрузки
        System.out.println("\n=== ДОКУМЕНТ ОТГРУЗКИ ===");
        ShipmentDocument shipment = new ShipmentDocument(
                2, "SHIP-2026-001", new Date(), DocumentStatus.DRAFT, "ООО Клиент"
        );
        shipment.removeProduct(laptop, 3);
        System.out.println("Остаток ноутбуков после отгрузки: " + laptop.getStock());

        // 9. Тестирование защиты от ошибок
        System.out.println("\n=== ПРОВЕРКА ЗАЩИТЫ ===");
        shipment.removeProduct(laptop, 100); // Попытка отгрузить больше, чем есть

        // 10. Проверка защиты от пустого документа
        System.out.println("\n=== ПРОВЕРКА ЗАЩИТЫ ОТ ПУСТОГО ДОКУМЕНТА ===");
        Document emptyDoc = new ReceiptDocument(
                3, "EMPTY-001", new Date(), DocumentStatus.DRAFT, "Тест"
        );
        try {
            emptyDoc.confirm();
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}