package nextstep.subway.line;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.line.dto.SectionResponse;

public class SectionAcceptanceTestResponse {
	public static void 지하철_노선에_지하철역_등록됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 지하철_노선에_등록한_구간_포함됨(ExtractableResponse<Response> response, List<Long> expectedStationIds) {
		List<Long> resultStationIds = response.jsonPath().getList("stations", SectionResponse.class).stream()
			.map(SectionResponse::getId)
			.collect(Collectors.toList());
		assertThat(resultStationIds).containsAll(expectedStationIds);
	}
}
