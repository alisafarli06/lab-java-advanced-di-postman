Design Choices for EarlyBirdDiscountService

The EarlyBirdDiscountService is a simple Java class with one method called calculateDiscount(String eventDate, String bookingDate). Inside this method, the given date strings are converted into LocalDate objects, and the number of days between them is calculated using ChronoUnit.DAYS.between().

If the booking is made at least 30 days before the event, the method returns a message saying that a 15% discount is applied. If it’s less than 30 days, then no discount is given. If the booking date is after the event date, it returns an error message.

The service is registered conditionally using DiscountFeatureConfig and the @ConditionalOnProperty annotation. This means the bean is only created when feature.earlybird.enabled=true is set in application.properties.

In the DiscountController, the service is injected through the constructor using Optional<EarlyBirdDiscountService>. This allows the controller to still work even if the bean is not loaded.

Questions & Answers
Why did you choose constructor-based DI for this lab?

I chose constructor-based dependency injection because it makes dependencies clear from the beginning. When you look at the constructor, you immediately see what the class depends on. It also makes the code easier to test since you can pass dependencies directly without needing the full Spring context.

Using Optional was helpful because the feature can be disabled. If the bean doesn’t exist, the application doesn’t crash immediately. It gives more flexibility in handling that situation.

What advantages do Postman pre-request and post-response scripts offer for automated testing?

Pre-request scripts are useful because they allow you to prepare something before the request is sent. For example, you can log information or set environment variables like a base URL.

Post-response (test) scripts are helpful for automatically checking the response. You can verify the status code, check if the correct discount message appears, and log results for debugging.

This saves time because you don’t need to manually check every response. With the Collection Runner, you can execute everything automatically.

How does your application behave when the early bird feature is disabled?

When feature.earlybird.enabled is set to false (or removed), Spring does not create the EarlyBirdDiscountService bean.

Since the controller receives an empty Optional, calling .get() would cause a NoSuchElementException. In a real-world application, it would be better to check isPresent() first and return something like a 503 Service Unavailable response or a clear message saying that the feature is currently disabled.

For this lab, it was kept simple, but in production this should definitely be handled more carefully.

What challenges did you face while integrating advanced DI with API testing?

One challenge was making sure the property name used in @ConditionalOnProperty exactly matched the one in application.properties. Even a small mismatch prevents the bean from loading.

Another challenge was deciding how to handle the case when the bean is missing. Using Optional worked well, but it needs to be handled properly to avoid runtime exceptions.

On the Postman side, writing test scripts that correctly validate dynamic response messages required paying close attention to the exact output format. Even small differences in wording could cause the tests to fail.
