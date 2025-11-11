package com.nistra.demy.admins.features.help.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nistra.demy.admins.R

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Help",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Volver a Ayuda",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = poppinsRegular,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            Text(
                text = "Política de Privacidad",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontFamily = poppinsBold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Text(
                text = "Última actualización: Noviembre 2025",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = poppinsRegular,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Text(
                text = """
1. Introducción  
En **Demy**, valoramos su privacidad y nos comprometemos a proteger su información personal.  
Esta Política de Privacidad explica cómo recopilamos, utilizamos, almacenamos y protegemos sus datos al usar nuestra plataforma de gestión académica.

2. Información que Recopilamos  
Podemos recopilar los siguientes tipos de información:  
• Datos personales (nombre, correo electrónico, número de teléfono).  
• Información académica (asistencia, calificaciones, cursos).  
• Datos de uso (dirección IP, tipo de dispositivo, navegador, actividad dentro de la plataforma).  
• Datos de facturación y pago, en caso de planes de suscripción.  

3. Uso de la Información  
La información recopilada se utiliza para:  
• Proporcionar, mantener y mejorar nuestros servicios.  
• Administrar cuentas de usuario y procesar pagos.  
• Personalizar la experiencia del usuario.  
• Enviar notificaciones importantes sobre la plataforma o actualizaciones.  
• Cumplir con obligaciones legales y de seguridad.  

4. Conservación de los Datos  
Sus datos serán almacenados de forma segura mientras su cuenta esté activa o según lo requiera la ley.  
Puede solicitar la eliminación de su información escribiendo a nuestro correo de contacto.

5. Seguridad de la Información  
Implementamos medidas técnicas y organizativas adecuadas para proteger sus datos contra el acceso no autorizado, la alteración, divulgación o destrucción.  
Sin embargo, ningún sistema es completamente seguro; al utilizar Demy, usted reconoce y acepta este riesgo.

6. Compartición de la Información  
No vendemos ni compartimos su información con terceros, salvo en los siguientes casos:  
• Cumplimiento de leyes o requerimientos judiciales.  
• Procesamiento de pagos a través de proveedores certificados.  
• Casos en los que usted otorgue su consentimiento explícito.  

7. Derechos del Usuario  
Usted tiene derecho a:  
• Acceder a los datos personales que mantenemos sobre usted.  
• Solicitar su rectificación o eliminación.  
• Oponerse al tratamiento de sus datos en ciertos casos.  
• Retirar su consentimiento en cualquier momento.  

8. Cookies y Tecnologías Similares  
Demy utiliza cookies para mejorar la experiencia del usuario, recordar preferencias y analizar el uso del sistema.  
Puede configurar su navegador para rechazar cookies, aunque algunas funciones podrían verse afectadas.

9. Cambios en la Política de Privacidad  
Podemos actualizar esta política en cualquier momento. Si se realizan cambios significativos, se notificará a los usuarios con antelación.  
El uso continuo de nuestros servicios implica la aceptación de la política revisada.

10. Contacto  
Si tiene preguntas sobre esta Política de Privacidad o desea ejercer sus derechos, comuníquese con nosotros:  
 privacy@demy.com  
 +51 927 230 192
                """.trimIndent(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = poppinsRegular,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp
                )
            )
        }
    }
}
