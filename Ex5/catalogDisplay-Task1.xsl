<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h3>Стойностите на всички track елементи, които имат стойност на атрибута length '4:04'</h3>
                <ul>
                    <!-- Минаваме през всички track елементи и извеждаме тяхната стойност, информацията е под формата на неномериран списък. -->
                    <xsl:for-each select=".//track">
                        <!-- Вземаме само тези, които имат атрибута length със стойност '4:04' -->
                        <xsl:if test="@length = '4:04'">
                            <li>
                                <xsl:value-of select="text()"/>
                            </li>
                        </xsl:if>
                    </xsl:for-each>
                </ul>

                <h3>Стойностите на всички track елементи, чиято дължина е по-малка от 15, както и техните дължини</h3>
                <ul>
                    <!-- Минаваме през всички track елементи и извеждаме тяхната стойност, информацията е под формата на неномериран списък. -->
                    <xsl:for-each select=".//track">
                        <!-- Вземаме само тези, които имат дължина по-малка от 15 -->
                        <xsl:if test="string-length() &lt; 15">
                            <li>
                                <xsl:value-of select="text()"/>
 -                                <xsl:value-of select="string-length()"/>
                            </li>
                        </xsl:if>
                    </xsl:for-each>
                </ul>

                <h3>Всички track елементи на четни/нечетни позиции</h3>
                <ul>
                    <!-- Минаваме през всички track елементи и извеждаме тяхната стойност, информацията е под формата на неномериран списък. -->
                    <xsl:for-each select=".//track">
                        <!-- Вземаме само тези, които са на четни позиции -->
                        <xsl:if test="position() mod 2 = 0">
                            <li>
                                <xsl:value-of select="text()"/>
                            </li>
                        </xsl:if>
                    </xsl:for-each>
                </ul>
                <ul>
                    <xsl:for-each select=".//track">
                        <!-- Вземаме само тези, които са на нечетни позиции -->
                        <xsl:if test="position() mod 2 != 0">
                            <li>
                                <xsl:value-of select="text()"/>
                            </li>
                        </xsl:if>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>