<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <ul>
                    <!-- Минаваме през всички track елементи -->
                    <xsl:for-each select=".//track">
                        <xsl:choose>
                            <!-- Проверки за дължината на името на песента -->
                            <xsl:when test="string-length() &gt; 15">
                                <!-- Извежда се типа на стринга според условието, стойността и дължината му -->
                                <li>A big string - 
                                    <xsl:value-of select="text()"/>
 - 
                                    <xsl:value-of select="string-length()"/>
                                </li>
                            </xsl:when>
                            <xsl:when test="string-length() &lt; 15">
                                <li>A small string - 
                                    <xsl:value-of select="text()"/>
 - 
                                    <xsl:value-of select="string-length()"/>
                                </li>
                            </xsl:when>
                            <xsl:otherwise>
                                <li>A medium string - 
                                    <xsl:value-of select="text()"/>
 - 
                                    <xsl:value-of select="string-length()"/>
                                </li>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>