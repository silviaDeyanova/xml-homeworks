<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="catalog/cd">
        <cd>
            <!-- Извеждане на информация за съответния елемент cd -->
            <title>
                <xsl:value-of select="title"/>
            </title>
            <!-- Копиране на елементи от входното XML дърво, tracklist и неговите поделементи (deep copy) -->
            <xsl:copy-of select="tracklist" />
        </cd>
    </xsl:template>
</xsl:stylesheet>