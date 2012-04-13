/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.volatility.surface.black;

import java.util.Collections;
import java.util.Set;

import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetType;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.financial.property.DefaultPropertyFunction;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public class ForexBlackVolatilitySurfaceMixedLogNormalDefaultPropertiesFunction extends DefaultPropertyFunction {
  private final String _timeAxis;
  private final String _yAxis;
  private final String _volatilityTransform;
  private final String _timeInterpolator;
  private final String _timeLeftExtrapolator;
  private final String _timeRightExtrapolator;
  private final String _weightingFunction;
  private final String _forwardCurveName;
  private final String _forwardCurveCalculationMethod;
  private final String _surfaceName;
  private final String _smileInterpolator;

  public ForexBlackVolatilitySurfaceMixedLogNormalDefaultPropertiesFunction(final String timeAxis, final String yAxis, final String volatilityTransform, final String timeInterpolator,
      final String timeLeftExtrapolator, final String timeRightExtrapolator, final String weightingFunction, final String forwardCurveName,
      final String forwardCurveCalculationMethod, final String surfaceName, final String smileInterpolator) {
    super(ComputationTargetType.PRIMITIVE, true);
    ArgumentChecker.notNull(timeAxis, "time axis");
    ArgumentChecker.notNull(yAxis, "y axis");
    ArgumentChecker.notNull(volatilityTransform, "volatility transform");
    ArgumentChecker.notNull(timeInterpolator, "time interpolator");
    ArgumentChecker.notNull(timeLeftExtrapolator, "time left extrapolator");
    ArgumentChecker.notNull(timeRightExtrapolator, "time right extrapolator");
    ArgumentChecker.notNull(weightingFunction, "weighting function");
    ArgumentChecker.notNull(forwardCurveName, "forward curve name");
    ArgumentChecker.notNull(forwardCurveCalculationMethod, "forward curve calculation method");
    ArgumentChecker.notNull(surfaceName, "surface name");
    ArgumentChecker.notNull(smileInterpolator, "smile interpolator");
    _timeAxis = timeAxis;
    _yAxis = yAxis;
    _volatilityTransform = volatilityTransform;
    _timeInterpolator = timeInterpolator;
    _timeLeftExtrapolator = timeLeftExtrapolator;
    _timeRightExtrapolator = timeRightExtrapolator;
    _weightingFunction = weightingFunction;
    _forwardCurveName = forwardCurveName;
    _forwardCurveCalculationMethod = forwardCurveCalculationMethod;
    _surfaceName = surfaceName;
    _smileInterpolator = smileInterpolator;
  }

  @Override
  protected void getDefaults(final PropertyDefaults defaults) {
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_AXIS);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_Y_AXIS);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_VOLATILITY_TRANSFORM);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_INTERPOLATOR);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_LEFT_EXTRAPOLATOR);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_RIGHT_EXTRAPOLATOR);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SPLINE_INTERPOLATOR);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SPLINE_LEFT_EXTRAPOLATOR);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_MIXED_LOG_NORMAL_WEIGHTING_FUNCTION);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, ValuePropertyNames.CURVE);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, ValuePropertyNames.CURVE_CALCULATION_METHOD);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, ValuePropertyNames.SURFACE);
    defaults.addValuePropertyName(ValueRequirementNames.BLACK_VOLATILITY_SURFACE, BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SMILE_INTERPOLATOR);
  }

  @Override
  protected Set<String> getDefaultValue(final FunctionCompilationContext context, final ComputationTarget target, final ValueRequirement desiredValue, final String propertyName) {
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_AXIS.equals(propertyName)) {
      return Collections.singleton(_timeAxis);
    }
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_Y_AXIS.equals(propertyName)) {
      return Collections.singleton(_yAxis);
    }
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_VOLATILITY_TRANSFORM.equals(propertyName)) {
      return Collections.singleton(_volatilityTransform);
    }
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_INTERPOLATOR.equals(propertyName)) {
      return Collections.singleton(_timeInterpolator);
    }
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_LEFT_EXTRAPOLATOR.equals(propertyName)) {
      return Collections.singleton(_timeLeftExtrapolator);
    }
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_TIME_RIGHT_EXTRAPOLATOR.equals(propertyName)) {
      return Collections.singleton(_timeRightExtrapolator);
    }
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_MIXED_LOG_NORMAL_WEIGHTING_FUNCTION.equals(propertyName)) {
      return Collections.singleton(_weightingFunction);
    }
    if (ValuePropertyNames.CURVE.equals(propertyName)) {
      return Collections.singleton(_forwardCurveName);
    }
    if (ValuePropertyNames.CURVE_CALCULATION_METHOD.equals(propertyName)) {
      return Collections.singleton(_forwardCurveCalculationMethod);
    }
    if (ValuePropertyNames.SURFACE.equals(propertyName)) {
      return Collections.singleton(_surfaceName);
    }
    if (BlackVolatilitySurfacePropertyNamesAndValues.PROPERTY_SMILE_INTERPOLATOR.equals(propertyName)) {
      return Collections.singleton(_smileInterpolator);
    }
    return null;
  }
}
