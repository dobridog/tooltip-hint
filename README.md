# Tooltip Hint
Helps users discover which views have tooltips, so they know exactly how to reveal the tooltip.

![tooltip hint demo](https://github.com/dobridog/tooltip-hint/blob/master/tooltip-hint.gif)

Integrates with native [Android Tooltips](https://developer.android.com/guide/topics/ui/tooltips.html) through TextView [Kotlin Extensions](https://kotlinlang.org/docs/reference/extensions.html).

### Download

Gradle dependency:
```groovy
dependencies {
   implementation 'com.kendle.tooltip:tooltip-hint:1.0.0'
}
```
For release notes see [releases.](https://github.com/dobridog/tooltip-hint/releases)

### Integration
There is no setup needed because Tooltip Hints are embedded into the `android.widget.TextView` class.

Start using `android:tooltipText` attribute in your layouts, or if using it already you can skip this section. See more about [Tooltips Designs](https://material.io/guidelines/components/tooltips.html#), [Tooltips](https://developer.android.com/guide/topics/ui/tooltips.html) and [TooltipsCompat](https://developer.android.com/reference/android/support/v7/widget/TooltipCompat.html).

```xml
 <AutoCompleteTextView
    android:tooltipText="@string/tooltip_email"
    android:id="@+id/email"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:inputType="textEmailAddress"
    />
```

To reveal a tooltip hint call View's `showTooltipHint()` method:

```java
AutoCompleteTextView view = findViewById(R.id.email);
view.showTooltipHint()
```

Or just as well you might want to do it for the whole layout:
```java
LinearLayout layout = findViewById(R.id.parent);
layout.showTooltipHint()
```

### Customization

When customizing Tooltip Hint style, you need to override the "TooltipHint" (case sensitive) style definition. Also, if you inherit style from "parent = _TooltipHint" that will ensure all defaults are set.

Supported customization items:
* **android:drawable** - tooltip hint icon
* **anchor** - position of the icon
* **android:drawablePadding** - padding around the icon

A custom style would look like this:

```xml
<style name="TooltipHint" parent="_TooltipHint">
    <item name="android:drawable">@drawable/ic_tooltip_hint</item>
    <item name="tooltipAnchor">right</item>
    <item name="android:drawablePadding">4dp</item>
</style>
```

### Sample app
Checkout [tooltip-hint-sample app](https://github.com/dobridog/tooltip-hint/tree/master/tooltip-hint-sample) for implementation details.