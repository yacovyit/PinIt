/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-02-18 22:11:37 UTC)
 * on 2016-03-17 at 12:08:45 UTC 
 * Modify at your own risk.
 */

package com.example.yacovyitzhak.myapplication.backend.wizardApi.model;

/**
 * Model definition for CursorBean.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the wizardApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class CursorBean extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer x;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer xWizzard;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer y;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer yWizzard;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getX() {
    return x;
  }

  /**
   * @param x x or {@code null} for none
   */
  public CursorBean setX(java.lang.Integer x) {
    this.x = x;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getXWizzard() {
    return xWizzard;
  }

  /**
   * @param xWizzard xWizzard or {@code null} for none
   */
  public CursorBean setXWizzard(java.lang.Integer xWizzard) {
    this.xWizzard = xWizzard;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getY() {
    return y;
  }

  /**
   * @param y y or {@code null} for none
   */
  public CursorBean setY(java.lang.Integer y) {
    this.y = y;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getYWizzard() {
    return yWizzard;
  }

  /**
   * @param yWizzard yWizzard or {@code null} for none
   */
  public CursorBean setYWizzard(java.lang.Integer yWizzard) {
    this.yWizzard = yWizzard;
    return this;
  }

  @Override
  public CursorBean set(String fieldName, Object value) {
    return (CursorBean) super.set(fieldName, value);
  }

  @Override
  public CursorBean clone() {
    return (CursorBean) super.clone();
  }

}