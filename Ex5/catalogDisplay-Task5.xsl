<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <!-- Деклариране на параметър -->
        <xsl:param name="title">Name of song: </xsl:param>
        <html>
            <head></head>
            <body>
                <h3>Ascending</h3>
                <!-- Минаваме през всички track елементи и ги сортираме в нарастващ ред, като това е сортирането по подразбиране -->
                <xsl:for-each select=".//track">
                    <xsl:sort select="." order="ascending"/>
                    <!-- Използване на параметъра -->
                    <xsl:value-of select="$title"/>
                    <!-- Представяме резултатите, оформени според указаното в условието -->
                    <xsl:value-of select="text()"/>
                    <xsl:text>_</xsl:text>
                    <xsl:value-of select="position()"/>
                    <!-- Проверка дали това е последния елемент, ако е, то след него няма запетая -->
                    <xsl:if test="position()!=last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>

                <!-- Аналогично и за същите елементи, но в намаляващ ред. -->
                <h3>Descending</h3>
                <xsl:for-each select=".//track">
                    <xsl:sort select="." order="descending"/>
                    <!-- Използване на параметъра -->
                    <xsl:value-of select="$title"/>
                    <xsl:value-of select="text()"/>
                    <xsl:text>_</xsl:text>
                    <xsl:value-of select="position()"/>
                    <xsl:if test="position()!=last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>