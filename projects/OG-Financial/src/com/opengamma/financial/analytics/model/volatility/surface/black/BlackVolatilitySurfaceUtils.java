/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.volatility.surface.black;

import static com.opengamma.engine.value.ValuePropertyNames.CURVE;
import static com.opengamma.engine.value.ValuePropertyNames.CURVE_CALCULATION_METHOD;
import static com.opengamma.engine.value.ValuePropertyNames.SURFACE;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.INTEGRATED_VARIANCE;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.LINEAR_TIME;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.LINEAR_Y;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.LOG_TIME;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.LOG_Y;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.MIXED_LOG_NORMAL;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_MIXED_LOG_NORMAL_WEIGHTING_FUNCTION;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SABR_EXTERNAL_BETA;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SABR_MODEL;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SABR_USE_EXTERNAL_BETA;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SABR_WEIGHTING_FUNCTION;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SMILE_INTERPOLATOR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SPLINE_INTERPOLATOR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SPLINE_LEFT_EXTRAPOLATOR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SPLINE_RIGHT_EXTRAPOLATOR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_AXIS;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_INTERPOLATOR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_LEFT_EXTRAPOLATOR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_RIGHT_EXTRAPOLATOR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_VOLATILITY_TRANSFORM;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_Y_AXIS;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.SABR;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.SPLINE;
import static com.opengamma.financial.analytics.model.volatility.surface.black.BlackVolatilitySurfacePropertyNamesAndValues.VARIANCE;

import java.util.Collections;
import java.util.Set;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.financial.analytics.model.InstrumentTypeProperties;

/**
 * 
 */
public class BlackVolatilitySurfaceUtils {

  static boolean useLogTime(final String property) {
    if (property.equals(LOG_TIME)) {
      return true;
    }
    if (property.equals(LINEAR_TIME)) {
      return false;
    }
    throw new OpenGammaRuntimeException("Could not recognise time axis property " + property);
  }

  static boolean useIntegratedVariance(final String property) {
    if (property.equals(INTEGRATED_VARIANCE)) {
      return true;
    }
    if (property.equals(VARIANCE)) {
      return false;
    }
    throw new OpenGammaRuntimeException("Could not recognise volatility transform property " + property);
  }

  static boolean useLogYAxis(final String property) {
    if (property.equals(LOG_Y)) {
      return true;
    }
    if (property.equals(LINEAR_Y)) {
      return false;
    }
    throw new OpenGammaRuntimeException("Could not recognise y-axis property type " + property);
  }

  public static ValueProperties.Builder addCommonVolatilityInterpolatorProperties(final ValueProperties properties, final String smileInterpolator) {
    return properties.copy()
        .withAny(PROPERTY_TIME_AXIS)
        .withAny(PROPERTY_Y_AXIS)
        .withAny(PROPERTY_VOLATILITY_TRANSFORM)
        .withAny(PROPERTY_TIME_INTERPOLATOR)
        .withAny(PROPERTY_TIME_LEFT_EXTRAPOLATOR)
        .withAny(PROPERTY_TIME_RIGHT_EXTRAPOLATOR)
        .with(PROPERTY_SMILE_INTERPOLATOR, smileInterpolator);
  }

  public static ValueProperties.Builder addCommonVolatilityInterpolatorProperties(final ValueProperties properties, final ValueRequirement desiredValue, final String smileInterpolator) {
    final String timeAxis = desiredValue.getConstraint(PROPERTY_TIME_AXIS);
    final String yAxis = desiredValue.getConstraint(PROPERTY_Y_AXIS);
    final String volatilityTransform = desiredValue.getConstraint(PROPERTY_VOLATILITY_TRANSFORM);
    final String timeInterpolator = desiredValue.getConstraint(PROPERTY_TIME_INTERPOLATOR);
    final String timeLeftExtrapolator = desiredValue.getConstraint(PROPERTY_TIME_LEFT_EXTRAPOLATOR);
    final String timeRightExtrapolator = desiredValue.getConstraint(PROPERTY_TIME_RIGHT_EXTRAPOLATOR);
    return properties.copy()
        .with(PROPERTY_TIME_AXIS, timeAxis)
        .with(PROPERTY_Y_AXIS, yAxis)
        .with(PROPERTY_VOLATILITY_TRANSFORM, volatilityTransform)
        .with(PROPERTY_TIME_INTERPOLATOR, timeInterpolator)
        .with(PROPERTY_TIME_LEFT_EXTRAPOLATOR, timeLeftExtrapolator)
        .with(PROPERTY_TIME_RIGHT_EXTRAPOLATOR, timeRightExtrapolator)
        .with(PROPERTY_SMILE_INTERPOLATOR, smileInterpolator);
  }

  public static Set<ValueRequirement> ensureCommonVolatilityInterpolatorProperties(final ValueProperties constraints) {
    final Set<String> timeAxisNames = constraints.getValues(PROPERTY_TIME_AXIS);
    if (timeAxisNames == null || timeAxisNames.size() != 1) {
      return null;
    }
    final Set<String> yAxisNames = constraints.getValues(PROPERTY_Y_AXIS);
    if (yAxisNames == null || yAxisNames.size() != 1) {
      return null;
    }
    final Set<String> volatilityTransformNames = constraints.getValues(PROPERTY_VOLATILITY_TRANSFORM);
    if (volatilityTransformNames == null || volatilityTransformNames.size() != 1) {
      return null;
    }
    final Set<String> timeInterpolatorNames = constraints.getValues(PROPERTY_TIME_INTERPOLATOR);
    if (timeInterpolatorNames == null || timeInterpolatorNames.size() != 1) {
      return null;
    }
    final Set<String> timeLeftExtrapolatorNames = constraints.getValues(PROPERTY_TIME_LEFT_EXTRAPOLATOR);
    if (timeLeftExtrapolatorNames == null || timeLeftExtrapolatorNames.size() != 1) {
      return null;
    }
    final Set<String> timeRightExtrapolatorNames = constraints.getValues(PROPERTY_TIME_RIGHT_EXTRAPOLATOR);
    if (timeRightExtrapolatorNames == null || timeRightExtrapolatorNames.size() != 1) {
      return null;
    }
    final Set<String> smileInterpolatorNames = constraints.getValues(PROPERTY_SMILE_INTERPOLATOR);
    if (smileInterpolatorNames == null || smileInterpolatorNames.size() != 1) {
      return null;
    }
    return Collections.emptySet();
  }

  public static ValueProperties.Builder addSplineVolatilityInterpolatorProperties(final ValueProperties properties) {
    return addCommonVolatilityInterpolatorProperties(properties.copy()
        .withAny(PROPERTY_SPLINE_INTERPOLATOR)
        .withAny(PROPERTY_SPLINE_LEFT_EXTRAPOLATOR)
        .withAny(PROPERTY_SPLINE_RIGHT_EXTRAPOLATOR).get(), SPLINE);
  }

  public static ValueProperties.Builder addSplineVolatilityInterpolatorProperties(final ValueProperties properties, final ValueRequirement desiredValue) {
    final String interpolatorName = desiredValue.getConstraint(PROPERTY_SPLINE_INTERPOLATOR);
    final String leftExtrapolatorName = desiredValue.getConstraint(PROPERTY_SPLINE_LEFT_EXTRAPOLATOR);
    final String rightExtrapolatorName = desiredValue.getConstraint(PROPERTY_SPLINE_RIGHT_EXTRAPOLATOR);
    return addCommonVolatilityInterpolatorProperties(properties.copy()
        .with(PROPERTY_SPLINE_INTERPOLATOR, interpolatorName)
        .with(PROPERTY_SPLINE_LEFT_EXTRAPOLATOR, leftExtrapolatorName)
        .with(PROPERTY_SPLINE_RIGHT_EXTRAPOLATOR, rightExtrapolatorName).get(), SPLINE);
  }

  public static Set<ValueRequirement> ensureSplineVolatilityInterpolatorProperties(final ValueProperties constraints) {
    final Set<String> interpolatorNames = constraints.getValues(PROPERTY_SPLINE_INTERPOLATOR);
    if (interpolatorNames == null || interpolatorNames.size() != 1) {
      return null;
    }
    final Set<String> leftExtrapolatorNames = constraints.getValues(PROPERTY_SPLINE_LEFT_EXTRAPOLATOR);
    if (leftExtrapolatorNames == null || leftExtrapolatorNames.size() != 1) {
      return null;
    }
    final Set<String> rightExtrapolatorNames = constraints.getValues(PROPERTY_SPLINE_RIGHT_EXTRAPOLATOR);
    if (rightExtrapolatorNames == null || rightExtrapolatorNames.size() != 1) {
      return null;
    }
    return Collections.emptySet();
  }

  public static ValueProperties.Builder addMixedLogNormalVolatilityInterpolatorProperties(final ValueProperties properties) {
    return addCommonVolatilityInterpolatorProperties(properties.copy()
        .withAny(PROPERTY_MIXED_LOG_NORMAL_WEIGHTING_FUNCTION).get(), MIXED_LOG_NORMAL);
  }

  public static ValueProperties.Builder addMixedLogNormalVolatilityInterpolatorProperties(final ValueProperties properties, final ValueRequirement desiredValue) {
    final String weightingFunction = desiredValue.getConstraint(PROPERTY_MIXED_LOG_NORMAL_WEIGHTING_FUNCTION);
    return addCommonVolatilityInterpolatorProperties(properties.copy()
        .with(PROPERTY_MIXED_LOG_NORMAL_WEIGHTING_FUNCTION, weightingFunction).get(), desiredValue, MIXED_LOG_NORMAL);
  }

  public static Set<ValueRequirement> ensureMixedLogNormalVolatilityInterpolatorProperties(final ValueProperties constraints) {
    final Set<ValueRequirement> requirements = ensureCommonVolatilityInterpolatorProperties(constraints);
    if (requirements == null) {
      return null;
    }
    final Set<String> weightingFunctionNames = constraints.getValues(PROPERTY_MIXED_LOG_NORMAL_WEIGHTING_FUNCTION);
    if (weightingFunctionNames == null || weightingFunctionNames.size() != 1) {
      return null;
    }
    return requirements;
  }

  public static ValueProperties.Builder addSABRVolatilityInterpolatorProperties(final ValueProperties properties) {
    return addCommonVolatilityInterpolatorProperties(properties.copy()
        .withAny(PROPERTY_SABR_MODEL)
        .withAny(PROPERTY_SABR_WEIGHTING_FUNCTION)
        .withAny(PROPERTY_SABR_USE_EXTERNAL_BETA)
        .withAny(PROPERTY_SABR_EXTERNAL_BETA).get(), SABR); // TODO optional?
  }

  public static ValueProperties.Builder addSABRVolatilityInterpolatorProperties(final ValueProperties properties, final ValueRequirement desiredValue) {
    final String model = desiredValue.getConstraint(PROPERTY_SABR_MODEL);
    final String weightingFunction = desiredValue.getConstraint(PROPERTY_SABR_WEIGHTING_FUNCTION);
    final String useExternalBeta = desiredValue.getConstraint(PROPERTY_SABR_USE_EXTERNAL_BETA);
    final String externalBeta = desiredValue.getConstraint(PROPERTY_SABR_EXTERNAL_BETA);
    return addCommonVolatilityInterpolatorProperties(properties.copy()
        .with(PROPERTY_SABR_MODEL, model)
        .with(PROPERTY_SABR_WEIGHTING_FUNCTION, weightingFunction)
        .with(PROPERTY_SABR_USE_EXTERNAL_BETA, useExternalBeta)
        .with(PROPERTY_SABR_EXTERNAL_BETA, externalBeta).get(), SABR); // TODO optional?
  }

  public static Set<ValueRequirement> ensureSABRVolatilityInterpolatorProperties(final ValueProperties constraints) {
    final Set<String> modelNames = constraints.getValues(PROPERTY_SABR_MODEL);
    if (modelNames == null || modelNames.size() != 1) {
      return null;
    }
    final Set<String> weightingFunctionNames = constraints.getValues(PROPERTY_SABR_WEIGHTING_FUNCTION);
    if (weightingFunctionNames == null || weightingFunctionNames.size() != 1) {
      return null;
    }
    final Set<String> useExternalBetaNames = constraints.getValues(PROPERTY_SABR_USE_EXTERNAL_BETA);
    if (useExternalBetaNames == null || useExternalBetaNames.size() != 1) {
      return null;
    }
    final Set<String> externalBetaNames = constraints.getValues(PROPERTY_SABR_EXTERNAL_BETA);
    if (externalBetaNames == null || externalBetaNames.size() != 1) {
      return null;
    }
    return Collections.emptySet();
  }

  public static ValueProperties.Builder addBlackSurfaceProperties(final ValueProperties properties, final String instrumentType) {
    return properties.copy()
        .withAny(SURFACE)
        .withAny(CURVE_CALCULATION_METHOD)
        .withAny(CURVE)
        .with(InstrumentTypeProperties.PROPERTY_SURFACE_INSTRUMENT_TYPE, instrumentType);
  }

  public static ValueProperties.Builder addBlackSurfaceProperties(final ValueProperties properties, final String instrumentType, final ValueRequirement desiredValue) {
    final String surface = desiredValue.getConstraint(SURFACE);
    final String forwardCurveCalculationMethod = desiredValue.getConstraint(CURVE_CALCULATION_METHOD);
    final String curve = desiredValue.getConstraint(CURVE);
    return properties.copy()
        .with(SURFACE, surface)
        .with(CURVE_CALCULATION_METHOD, forwardCurveCalculationMethod)
        .with(CURVE, curve)
        .with(InstrumentTypeProperties.PROPERTY_SURFACE_INSTRUMENT_TYPE, instrumentType);
  }

  public static ValueProperties.Builder getVolatilityInterpolatorProperties(final ValueProperties properties, final ValueRequirement desiredValue) {
    final String smileInterpolator = desiredValue.getConstraint(PROPERTY_SMILE_INTERPOLATOR);
    if (smileInterpolator.equals(SPLINE)) {
      return BlackVolatilitySurfaceUtils.addSplineVolatilityInterpolatorProperties(ValueProperties.builder().get(), desiredValue);
    }
    if (smileInterpolator.equals(SABR)) {
      return BlackVolatilitySurfaceUtils.addSABRVolatilityInterpolatorProperties(ValueProperties.builder().get(), desiredValue);
    }
    if (smileInterpolator.equals(MIXED_LOG_NORMAL)) {
      return BlackVolatilitySurfaceUtils.addMixedLogNormalVolatilityInterpolatorProperties(ValueProperties.builder().get(), desiredValue);
    }
    throw new OpenGammaRuntimeException("Did not recognise smile interpolator type " + smileInterpolator);
  }

}
