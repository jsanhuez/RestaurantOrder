# Proyecto Restaurant Order

Aplicación Android para la toma de pedidos, cálculo de subtotales y gestión de propinas.

## Autor

Juan Sanhueza R.

## 1. Clases Modelo

Implementadas en el paquete `model` siguiendo el diagrama de clases:

- `ItemMenu`: Define plato y precio.
- `ItemMesa`: Gestiona cantidad y subtotal por ítem.
- `CuentaMesa`: Lógica global de totales y cálculo opcional de propina (10%).

## 2. Interfaz (ConstraintLayout)

Diseño responsivo que incluye:

- `ImageView` para logo y platos.
- `EditText` numéricos para entrada de cantidades.
- `SwitchCompat` para activación de propina.
- `TextView` para visualización de montos dinámicos.

## 3. MainActivity e Integración

- **Integración**: Vincula la UI con las clases de negocio.
- **Eventos**: Uso de `TextWatcher` en entradas de cantidad y `CheckedChangeListener` en el switch para actualización inmediata de montos.
- **Formato**: Valores monetarios formateados a CLP usando `NumberFormat`.
