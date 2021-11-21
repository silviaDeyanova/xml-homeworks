<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <xsl:apply-templates select="catalog"/>
            </body>
        </html>
    </xsl:template>
    <!-- Представяне на данните на всеки елемент cd от каталога - заглавие, година и песни под формата 
    на номериран списък -->
    <xsl:template match="cd">
        <xsl:apply-templates select="title"/>
        <br/>
        <xsl:apply-templates select="year"/>
        <ol>
            <!-- Прилагане на темплейта за всеки track елементи с или без атрибути -->
            <xsl:apply-templates select="tracklist/track" mode="withoutAttributes"/>
            <xsl:apply-templates select="tracklist/track" mode="withAttributes"/>
        </ol>
    </xsl:template>
    <!-- Темплейт за всеки track елемент от tracklist с атрибути -->
    <xsl:template match="catalog/cd/tracklist/track" mode="withAttributes">
        <li>
            <xsl:apply-templates select="text()"/>
            <xsl:for-each select="@*">
                <br/>
                <b>Atribute name:</b>
                <xsl:value-of select="name()"/>
 -                <b>atribute value:</b>
                <xsl:value-of select="."/>
            </xsl:for-each>
        </li>
    </xsl:template>
    <!-- Темплейт за всеки track елемент от tracklist без атрибути -->
    <xsl:template match="catalog/cd/tracklist/track" mode="withoutAttributes">
        <li>
            <xsl:apply-templates select="text()"/>
        </li>
    </xsl:template>
</xsl:stylesheet>