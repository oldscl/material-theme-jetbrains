/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.chrisrm.idea.annotators.settings;

import com.chrisrm.idea.annotators.JavaAnnotator;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings("DuplicateStringLiteralInspection")
public final class JavaColorSettings extends BaseColorSettings {
  @NotNull
  @NonNls
  private static final AttributesDescriptor[] JAVA_ATTRIBUTES;
  @NonNls
  private static final Map<String, TextAttributesKey> JAVA_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey JAVA_KEYWORD = JavaAnnotator.JAVA_KEYWORD;
  private static final TextAttributesKey THIS_SUPER = JavaAnnotator.THIS_SUPER;
  private static final TextAttributesKey MODIFIER = JavaAnnotator.MODIFIER;
  private static final TextAttributesKey STATIC_FINAL = JavaAnnotator.STATIC_FINAL;

  static {
    JAVA_ATTRIBUTES = new AttributesDescriptor[] {
        new AttributesDescriptor(MaterialThemeBundle.message("keywords.this.super"), THIS_SUPER),
        new AttributesDescriptor(MaterialThemeBundle.message("keywords.private.public.protected"), MODIFIER),
        new AttributesDescriptor(MaterialThemeBundle.message("keywords.static.final"), STATIC_FINAL),
    };

    JAVA_DESCRIPTORS.putAll(createAdditionalHlAttrs());
  }

  @NotNull
  @SuppressWarnings("OverlyLongMethod")
  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    @NonNls final Map<String, TextAttributesKey> descriptors = new THashMap<>();

    descriptors.put("field", ObjectUtils.notNull(TextAttributesKey.find("INSTANCE_FIELD_ATTRIBUTES"),
                                                 DefaultLanguageHighlighterColors.INSTANCE_FIELD));
    descriptors.put("unusedField", CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES);
    descriptors.put("error", CodeInsightColors.ERRORS_ATTRIBUTES);
    descriptors.put("warning", CodeInsightColors.WARNINGS_ATTRIBUTES);
    descriptors.put("weak_warning", CodeInsightColors.WEAK_WARNING_ATTRIBUTES);
    descriptors.put("server_problems", CodeInsightColors.GENERIC_SERVER_ERROR_OR_WARNING);
    descriptors.put("server_duplicate", CodeInsightColors.DUPLICATE_FROM_SERVER);
    descriptors.put("unknownType", CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
    descriptors.put("localVar", ObjectUtils.notNull(TextAttributesKey.find("LOCAL_VARIABLE_ATTRIBUTES"),
                                                    DefaultLanguageHighlighterColors.LOCAL_VARIABLE));
    descriptors.put("reassignedLocalVar", ObjectUtils.notNull(TextAttributesKey.find("REASSIGNED_LOCAL_VARIABLE_ATTRIBUTES"),
                                                              DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE));
    descriptors.put("reassignedParameter", ObjectUtils.notNull(TextAttributesKey.find("REASSIGNED_PARAMETER_ATTRIBUTES);"),
                                                               DefaultLanguageHighlighterColors.REASSIGNED_PARAMETER));
    descriptors.put("implicitAnonymousParameter",
                    ObjectUtils.notNull(TextAttributesKey.find("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES);"),
                                        DefaultLanguageHighlighterColors.CLASS_NAME));
    descriptors.put("static", ObjectUtils.notNull(TextAttributesKey.find("STATIC_FIELD_ATTRIBUTES);"),
                                                  DefaultLanguageHighlighterColors.STATIC_FIELD));
    descriptors.put("static_final", ObjectUtils.notNull(TextAttributesKey.find("STATIC_FINAL_FIELD_ATTRIBUTES);"),
                                                        DefaultLanguageHighlighterColors.STATIC_FIELD));
    descriptors.put("deprecated", CodeInsightColors.DEPRECATED_ATTRIBUTES);
    descriptors.put("for_removal", CodeInsightColors.MARKED_FOR_REMOVAL_ATTRIBUTES);
    descriptors.put("constructorCall", ObjectUtils.notNull(TextAttributesKey.find("CONSTRUCTOR_CALL_ATTRIBUTES);"),
                                                           DefaultLanguageHighlighterColors.FUNCTION_CALL));
    descriptors.put("constructorDeclaration", ObjectUtils.notNull(TextAttributesKey.find("CONSTRUCTOR_DECLARATION_ATTRIBUTES);"),
                                                                  DefaultLanguageHighlighterColors.FUNCTION_DECLARATION));
    descriptors.put("methodCall", ObjectUtils.notNull(TextAttributesKey.find("METHOD_CALL_ATTRIBUTES);"),
                                                      DefaultLanguageHighlighterColors.FUNCTION_CALL));
    descriptors.put("methodDeclaration", ObjectUtils.notNull(TextAttributesKey.find("METHOD_DECLARATION_ATTRIBUTES);"),
                                                             DefaultLanguageHighlighterColors.FUNCTION_DECLARATION));
    descriptors.put("static_method", ObjectUtils.notNull(TextAttributesKey.find("STATIC_METHOD_ATTRIBUTES);"),
                                                         DefaultLanguageHighlighterColors.STATIC_METHOD));
    descriptors.put("abstract_method", ObjectUtils.notNull(TextAttributesKey.find("ABSTRACT_METHOD_ATTRIBUTES);"),
                                                           DefaultLanguageHighlighterColors.FUNCTION_CALL));
    descriptors.put("inherited_method", ObjectUtils.notNull(TextAttributesKey.find("INHERITED_METHOD_ATTRIBUTES);"),
                                                            DefaultLanguageHighlighterColors.FUNCTION_CALL));
    descriptors.put("param", ObjectUtils.notNull(TextAttributesKey.find("PARAMETER_ATTRIBUTES);"),
                                                 DefaultLanguageHighlighterColors.PARAMETER));
    descriptors.put("lambda_param", ObjectUtils.notNull(TextAttributesKey.find("LAMBDA_PARAMETER_ATTRIBUTES);"),
                                                        DefaultLanguageHighlighterColors.PARAMETER));
    descriptors.put("class", ObjectUtils.notNull(TextAttributesKey.find("CLASS_NAME_ATTRIBUTES);"),
                                                 DefaultLanguageHighlighterColors.CLASS_NAME));
    descriptors.put("anonymousClass", ObjectUtils.notNull(TextAttributesKey.find("ANONYMOUS_CLASS_NAME_ATTRIBUTES);"),
                                                          DefaultLanguageHighlighterColors.CLASS_NAME));
    descriptors.put("typeParameter", ObjectUtils.notNull(TextAttributesKey.find("TYPE_PARAMETER_NAME_ATTRIBUTES);"),
                                                         DefaultLanguageHighlighterColors.PARAMETER));
    descriptors.put("abstractClass", ObjectUtils.notNull(TextAttributesKey.find("ABSTRACT_CLASS_NAME_ATTRIBUTES);"),
                                                         DefaultLanguageHighlighterColors.CLASS_NAME));
    descriptors.put("interface", ObjectUtils.notNull(TextAttributesKey.find("INTERFACE_NAME_ATTRIBUTES);"),
                                                     DefaultLanguageHighlighterColors.INTERFACE_NAME));
    descriptors.put("enum", ObjectUtils.notNull(TextAttributesKey.find("ENUM_NAME_ATTRIBUTES);"),
                                                DefaultLanguageHighlighterColors.CLASS_NAME));
    descriptors.put("annotationName", ObjectUtils.notNull(TextAttributesKey.find("ANNOTATION_NAME_ATTRIBUTES);"),
                                                          DefaultLanguageHighlighterColors.METADATA));
    descriptors.put("annotationAttributeName", ObjectUtils.notNull(TextAttributesKey.find("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES);"),
                                                                   DefaultLanguageHighlighterColors.METADATA));
    descriptors.put("javadocTagValue", ObjectUtils.notNull(TextAttributesKey.find("DOC_COMMENT_TAG_VALUE);"),
                                                           DefaultLanguageHighlighterColors.DOC_COMMENT_TAG_VALUE));
    descriptors.put("instanceFinalField", ObjectUtils.notNull(TextAttributesKey.find("INSTANCE_FINAL_FIELD_ATTRIBUTES);"),
                                                              DefaultLanguageHighlighterColors.INSTANCE_FIELD));
    descriptors.put("staticallyConstImported", ObjectUtils.notNull(TextAttributesKey.find("STATIC_FINAL_FIELD_IMPORTED_ATTRIBUTES);"),
                                                                   DefaultLanguageHighlighterColors.STATIC_FIELD));
    descriptors.put("staticallyImported", ObjectUtils.notNull(TextAttributesKey.find("STATIC_FIELD_IMPORTED_ATTRIBUTES);"),
                                                              DefaultLanguageHighlighterColors.STATIC_FIELD));
    descriptors.put("static_imported_method", ObjectUtils.notNull(TextAttributesKey.find("STATIC_METHOD_CALL_IMPORTED_ATTRIBUTES);"),
                                                                  DefaultLanguageHighlighterColors.STATIC_METHOD));

    descriptors.put("keyword", JAVA_KEYWORD);
    descriptors.put("this", THIS_SUPER);
    descriptors.put("sf", STATIC_FINAL);
    descriptors.put("modifier", MODIFIER);

    return descriptors;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.Java;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("JAVA"), Language.ANY); //NON-NLS
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NonNls
  @NotNull
  @Override
  public String getDemoText() {
    return
        "public class <class>SomeClass</class> extends <class>BaseClass</class> {\n" +
        "  <modifier>private</modifier> <sf>static final</sf> <field>field</field> = null;\n" +
        "  <modifier>protected</modifier> <sf>final</sf> <field>otherField</field>;\n\n" +
        "  <modifier>public</modifier> <constructorDeclaration>SomeClass</constructorDeclaration>(<interface>AnInterface</interface> " +
        "<param>param1</param>, int[] <reassignedParameter>reassignedParam</reassignedParameter>,\n" +
        "                  int <param>param2</param>\n" +
        "                  int <param>param3</param>) {\n" +
        "    <this>super</this>(<param>param1</param>);\n" +
        "    <this>this</this>.<warning>field</warning> = <param>param1</param>;\n" +
        "  }\n " +
        "}\n";
  }

  @NotNull
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return Collections.unmodifiableMap(JAVA_DESCRIPTORS);
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return JAVA_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("keywords.java.additions");
  }

  @NotNull
  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isIntelliJ() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
