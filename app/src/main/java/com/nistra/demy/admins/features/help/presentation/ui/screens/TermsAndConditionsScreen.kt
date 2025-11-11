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
fun TermsAndConditionsScreen(navController: NavController) {
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
                text = "Términos y Condiciones",
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
1. Aceptación de los Términos  
Al acceder y utilizar la plataforma **Demy**, usted acepta estar sujeto a estos términos y condiciones de uso. Demy es una plataforma de gestión académica diseñada para optimizar la administración de academias, centros educativos y organizaciones de enseñanza.

2. Descripción del Servicio  
Demy proporciona herramientas de gestión académica que incluyen:  
• Gestión de matrículas y estudiantes  
• Control de horarios y clases  
• Administración de pagos y facturación  
• Seguimiento académico y reportes  
• Comunicación con estudiantes y padres de familia  
• Gestión de profesores y personal administrativo  

3. Cuentas de Usuario  
Para acceder a los servicios de Demy, debe crear una cuenta proporcionando información precisa y actualizada. Es responsable de mantener la confidencialidad de su contraseña y de todas las actividades que ocurran bajo su cuenta. Debe notificarnos inmediatamente cualquier uso no autorizado de su cuenta.

4. Uso Aceptable  
Usted se compromete a utilizar Demy únicamente para fines legítimos de gestión académica. Está prohibido:  
• Usar la plataforma para actividades ilegales o no autorizadas  
• Interferir con el funcionamiento normal de la plataforma  
• Intentar acceder a cuentas de otros usuarios  
• Compartir información falsa o engañosa  
• Violar los derechos de propiedad intelectual  

5. Datos y Contenido  
Usted conserva todos los derechos sobre los datos académicos que ingrese en Demy. Al usar nuestros servicios, nos otorga una licencia limitada para procesar, almacenar y respaldar sus datos únicamente para proporcionar el servicio. Nos comprometemos a mantener la confidencialidad de la información académica sensible.

6. Facturación y Pagos  
Los servicios de Demy están disponibles bajo planes de suscripción mensual o anual. Los pagos se procesan de forma segura y se renuevan automáticamente a menos que cancele su suscripción. Puede cancelar su suscripción en cualquier momento desde su panel de control.

7. Limitación de Responsabilidad  
Demy se proporciona "tal como está". No garantizamos que el servicio esté libre de errores o interrupciones. Nuestra responsabilidad se limita al monto pagado por el servicio en los 12 meses anteriores al evento que dio lugar al reclamo.

8. Terminación  
Podemos suspender o terminar su acceso a Demy si viola estos términos. Al terminar el servicio, sus datos permanecerán disponibles para exportación durante 30 días, después de los cuales serán eliminados permanentemente.

9. Modificaciones  
Nos reservamos el derecho de modificar estos términos. Los cambios significativos serán notificados con 30 días de anticipación. El uso continuado del servicio constituye aceptación de los nuevos términos.

10. Contacto  
Para consultas sobre estos términos o sobre nuestros servicios, contáctenos en:  
 contact_us@demy.com  
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
