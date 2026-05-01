import os
from dotenv import load_dotenv
import psycopg2
import pandas as pd
import matplotlib.pyplot as plt  
from datetime import datetime

# Configuración de carpetas para mantener el proyecto limpio
CARPETA_REPORTES = 'reportes_generados'
if not os.path.exists(CARPETA_REPORTES):
    os.makedirs(CARPETA_REPORTES)

fecha_actual = datetime.now().strftime("%Y-%m-%d_%H-%M")

try:
    print("--- INICIANDO SISTEMA DE ANÁLISIS DE INVENTARIO ---")
    
    # Cargar las variables del archivo .env
    load_dotenv()

    # Usar las variables cargadas
    conexion = psycopg2.connect(
    host="localhost",
    port="5432",
    database=os.getenv('DB_NAME'),
    user=os.getenv('DB_USER'),
    password=os.getenv('DB_PASSWORD')
)


    # 2. Extracción y Limpieza de Datos
    query = "SELECT id, nombre, sku, cantidad_actual, stock_minimo, precio, categoria FROM producto;"
    df = pd.read_sql_query(query, conexion)
    
    # Calculamos el valor financiero de cada fila
    df['valor_total_inventario'] = df['cantidad_actual'] * df['precio']

    # --- TABLA COMPLETA EN TERMINAL ---
    print("\n" + "="*60)
    print("                 INVENTARIO COMPLETO")
    print("="*60)
    # Mostramos las columnas más importantes sin el índice numérico para que se vea limpio
    print(df[['sku', 'nombre', 'cantidad_actual', 'stock_minimo', 'precio']].to_string(index=False))

    # --- BLOQUE 1: RESUMEN EN CONSOLA ---
    print("\n" + "="*40)
    print("       RESUMEN EJECUTIVO")
    print("="*40)
    valor_total_almacen = df['valor_total_inventario'].sum()
    print(f"Inversión total en almacén: ${valor_total_almacen:,.2f}")
    
    alertas_df = df[df['cantidad_actual'] <= df['stock_minimo']]
    print(f"Productos en riesgo de desabastecimiento: {len(alertas_df)}")
    
    if not alertas_df.empty:
        print("\n--- LISTA DE ALERTAS ---")
        for _, fila in alertas_df.iterrows():
            print(f"[!] {fila['nombre']} (SKU: {fila['sku']}) - Quedan {fila['cantidad_actual']} unidades (Mínimo: {fila['stock_minimo']})")

    # --- BLOQUE 2: GENERACIÓN DE EXCEL ---
    excel_file = f'{CARPETA_REPORTES}/Reporte_{fecha_actual}.xlsx'
    with pd.ExcelWriter(excel_file, engine='openpyxl') as writer:
        # Hoja 1: Datos completos
        df.to_excel(writer, sheet_name='Inventario Completo', index=False)
        
        # Hoja 2: Solo Alertas
        alertas_df.to_excel(writer, sheet_name='Alertas de Reabastecimiento', index=False)
        
        # Hoja 3: Resumen por Categoría
        resumen_cat = df.groupby('categoria').agg({
            'id': 'count',
            'valor_total_inventario': 'sum'
        }).rename(columns={'id': 'Total Productos', 'valor_total_inventario': 'Valor Invertido'})
        resumen_cat.to_excel(writer, sheet_name='Resumen por Categoría')

    print(f"\n[OK] Archivo Excel generado: {excel_file}")

    # --- BLOQUE 3: DASHBOARD VISUAL (GRÁFICAS) ---
    fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(15, 6))
    fig.patch.set_facecolor('#f4f4f4')

    # Gráfica A: Barras de Comparación de Stock
    colores = ['#e74c3c' if x <= y else '#2ecc71' for x, y in zip(df['cantidad_actual'], df['stock_minimo'])]
    ax1.bar(df['nombre'], df['cantidad_actual'], color=colores, label='Stock Actual')
    ax1.step(df['nombre'], df['stock_minimo'], where='mid', color='#34495e', linestyle='--', label='Nivel Crítico', linewidth=2)
    ax1.set_title('Estado de Stock por Producto', fontsize=12, fontweight='bold')
    ax1.set_ylabel('Unidades')
    ax1.tick_params(axis='x', rotation=30)
    ax1.legend()

    # Gráfica B: Inversión por Categoría (Pie Chart)
    inv_cat = df.groupby('categoria')['valor_total_inventario'].sum()
    ax2.pie(inv_cat, labels=inv_cat.index, autopct='%1.1f%%', startangle=140, colors=['#3498db', '#9b59b6', '#f1c40f', '#e67e22'])
    ax2.set_title('Distribución de Inversión ($)', fontsize=12, fontweight='bold')

    plt.tight_layout()
    img_file = f'{CARPETA_REPORTES}/Dashboard_{fecha_actual}.png'
    plt.savefig(img_file)
    print(f"[OK] Imagen del Dashboard guardada: {img_file}")
    
    print("\n--- PROCESO FINALIZADO CON ÉXITO ---")
    plt.show()

except Exception as e:
    print(f"ERROR CRÍTICO: {e}")
finally:
    if 'conexion' in locals():
        conexion.close()